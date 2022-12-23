package com.daw.cinema.service;

import com.daw.cinema.dto.LoginCredentialsDto;
import com.daw.cinema.dto.TokenDto;
import com.daw.cinema.entity.User;
import com.daw.cinema.enums.UserRole;
import com.daw.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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
        User user = userRepository.findUserByEmail(userDetails.getUsername()).get();
        String token = jwtUtility.generateToken(userDetails, user.getId());

        return new TokenDto(token, jwtUtility.getExpirationDate(token), user.getId(), user.getEmail(), user.getRole());
    }

    public TokenDto decodeToken(String token) {
        Map<String, Object> stringObjectMap = jwtUtility.parseClaims(token);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);
        tokenDto.setUsername(stringObjectMap.get("sub").toString());
        tokenDto.setUserId(Long.valueOf(stringObjectMap.get("userId").toString()));
        tokenDto.setRole(UserRole.USER);
        LocalDateTime expirationDate = jwtUtility.getExpirationDate(token);
        tokenDto.setExpirationDate(expirationDate);
        String roles = stringObjectMap.get("roles").toString();
        if (roles.contains("ADMIN")) {
            tokenDto.setRole(UserRole.ADMIN);
        }
        return tokenDto;
    }
}
