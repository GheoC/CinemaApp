package com.daw.cinema.mapper;

import com.daw.cinema.dto.MovieDto;
import com.daw.cinema.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

  @Mapping(target = "id", ignore = true)
  Movie toEntity(MovieDto movieDto);

  MovieDto toDto(Movie movie);
}
