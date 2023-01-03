package com.daw.cinema.mapper;

import com.daw.cinema.dto.MovieEventDto;
import com.daw.cinema.entity.MovieEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieEventMapper {
  @Mapping(target = "movie.id", source = "movieDtoId")
  MovieEvent toEntity(MovieEventDto movieEventDto);

  @Mapping(target = "movieDtoId", source = "movie.id")
  MovieEventDto toDto(MovieEvent movieEvent);
}
