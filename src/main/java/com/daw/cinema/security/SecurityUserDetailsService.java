package com.daw.cinema.security;

import com.daw.cinema.entity.User;
import com.daw.cinema.enums.UserRole;
import com.daw.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    User userFromDatabase =
        userRepository
            .findUserByEmail(username)
            .orElseThrow(() -> new RuntimeException("User not found!"));

    UserDetails userDetails =
        new org.springframework.security.core.userdetails.User(
            userFromDatabase.getEmail(),
            userFromDatabase.getPassword(),
            getAuthority(userFromDatabase));

    return userDetails;
  }

  private Set<SimpleGrantedAuthority> getAuthority(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
    if (user.getRole() == UserRole.ADMIN) {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
    }
    return authorities;
  }
}
