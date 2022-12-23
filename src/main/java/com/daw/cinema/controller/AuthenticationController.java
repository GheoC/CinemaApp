package com.daw.cinema.controller;

import com.daw.cinema.dto.LoginCredentialsDto;
import com.daw.cinema.dto.TokenDto;
import com.daw.cinema.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> login(@RequestBody LoginCredentialsDto loginCredentials) {
        return ResponseEntity.ok(authenticationService.authenticate(loginCredentials));
    }

    @GetMapping("/check-token/{token}")
    public TokenDto checkToken(@PathVariable String token) {
        return authenticationService.decodeToken(token);
    }
}
