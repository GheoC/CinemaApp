package com.daw.cinema.security.evaluator;

import com.daw.cinema.entity.User;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AuthorizationEvaluator {
    private final UserRepository userRepository;

    public boolean isAccountOwner(Long id) {
        return id == getAuthUserId();
    }

    private Long getAuthUserId() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByEmail = userRepository.findUserByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userByEmail.getId();
    }
}
