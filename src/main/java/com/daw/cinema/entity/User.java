package com.daw.cinema.entity;

import com.daw.cinema.enums.UserRole;
import com.daw.cinema.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String password;

  @Enumerated(value = EnumType.STRING)
  private UserRole role;

  @Enumerated(value = EnumType.STRING)
  private UserStatus status;
}
