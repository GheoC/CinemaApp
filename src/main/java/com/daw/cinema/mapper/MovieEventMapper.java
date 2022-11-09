package com.daw.cinema.mapper;

import com.daw.cinema.dto.MovieEventDto;
import com.daw.cinema.entity.MovieEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {MovieMapper.class})
public interface MovieEventMapper {
  @Mapping(target = "movie.id", source = "movieDto.id")
  MovieEvent toEntity(MovieEventDto movieEventDto);

  @Mapping(target = "movieDto", source = "movie")
  MovieEventDto toDto(MovieEvent movieEvent);
}
