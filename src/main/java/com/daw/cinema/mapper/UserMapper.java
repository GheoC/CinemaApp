package com.daw.cinema.mapper;

import com.daw.cinema.dto.UserDto;
import com.daw.cinema.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toEntity(UserDto dto);

  @Mapping(target = "password", constant = "CONFIDENTIAL")
  UserDto toDto(User user);
}
