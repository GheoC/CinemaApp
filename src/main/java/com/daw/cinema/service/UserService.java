package com.daw.cinema.service;

import com.daw.cinema.entity.User;
import com.daw.cinema.enums.UserRole;
import com.daw.cinema.enums.UserStatus;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        user.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User NOT found"));
    }

    public void delete(Long id) {
        userRepository.delete(getUser(id));
    }

    public void changeStatus(Long id) {
        User userFromDb = getUser(id);
        userFromDb.setStatus(userFromDb.getStatus() == UserStatus.ACTIVE ? UserStatus.INACTIVE : UserStatus.ACTIVE);
        userRepository.save(userFromDb);
    }
}
