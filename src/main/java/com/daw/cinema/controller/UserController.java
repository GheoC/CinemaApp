package com.daw.cinema.controller;

import com.daw.cinema.dto.UserDto;
import com.daw.cinema.entity.User;
import com.daw.cinema.mapper.UserMapper;
import com.daw.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping("/api/v1/users")
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserDto> getAllUsers() {
    return userService.getAll().stream().map(userMapper::toDto).toList();
  }

  @PostMapping("/api/v1/users")
  public UserDto createUser(@RequestBody UserDto userDto) {
    return userMapper.toDto(userService.create(userMapper.toEntity(userDto)));
  }
}
