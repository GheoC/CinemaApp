package com.daw.cinema.dto;

import com.daw.cinema.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenDto {
  private String token;
  private LocalDateTime expirationDate;
  private Long userId;
  private String username;
  private UserRole role;
}
