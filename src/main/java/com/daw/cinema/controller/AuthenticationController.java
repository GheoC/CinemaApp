package com.daw.cinema.controller;

import com.daw.cinema.dto.LoginCredentialsDto;
import com.daw.cinema.dto.TokenDto;
import com.daw.cinema.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping
  public ResponseEntity<TokenDto> login(@RequestBody LoginCredentialsDto loginCredentials) {
    return ResponseEntity.ok(authenticationService.authenticate(loginCredentials));
  }
}
