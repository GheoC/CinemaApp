package com.daw.cinema.service;

import com.daw.cinema.dto.LoginCredentialsDto;
import com.daw.cinema.dto.TokenDto;
import com.daw.cinema.entity.User;
import com.daw.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtUtility;
  private final UserRepository userRepository;

  public TokenDto authenticate(LoginCredentialsDto loginCredentials) {
    Authentication authenticated;
    try {
      authenticated =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginCredentials.getUsername(), loginCredentials.getPassword()));
    } catch (AuthenticationException e) {
      throw new BadCredentialsException(e.getMessage());
    }
    UserDetails userDetails = (UserDetails) authenticated.getPrincipal();
    String token = jwtUtility.generateToken(userDetails);

    User user = userRepository.findUserByEmail(loginCredentials.getUsername()).get();

    return new TokenDto(token, jwtUtility.getExpirationDate(token), user.getId());
  }
}
