package com.daw.cinema.controller;

import com.daw.cinema.dto.UserDto;
import com.daw.cinema.mapper.UserMapper;
import com.daw.cinema.service.UserService;
import com.daw.cinema.validation.discriminator.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost/3000")
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping("/api/v1/users")
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserDto> getAllUsers() {
    return userService.getAll().stream().map(userMapper::toDto).toList();
  }

  @PostMapping("/api/v1/users")
  public UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
    return userMapper.toDto(userService.create(userMapper.toEntity(userDto)));
  }

  @GetMapping("/api/v1/users/{id}")
  public UserDto getUser(@PathVariable Long id)
  {
    return userMapper.toDto(userService.getUser(id));
  }
}
