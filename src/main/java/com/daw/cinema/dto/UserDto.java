package com.daw.cinema.dto;

import com.daw.cinema.enums.UserRole;
import com.daw.cinema.validation.discriminator.OnCreate;
import com.daw.cinema.validation.discriminator.OnUpdate;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
public class UserDto {
  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  private Long id;

  @NotEmpty private String firstName;
  @NotEmpty private String lastName;
  @Email private String email;

  @Pattern(regexp = "^07\\d{8}$", message = "Wrong phone number format")
  @NotNull(message = "phone number must NOT be null")
  private String phoneNumber;

  @NotEmpty private String password;

  @Null private UserRole role;
}
