package com.daw.cinema.mapper;

import com.daw.cinema.dto.MovieDto;
import com.daw.cinema.entity.Celebrity;
import com.daw.cinema.entity.Genre;
import com.daw.cinema.entity.Movie;
import com.daw.cinema.enums.MovieGenre;
import com.daw.cinema.service.CelebrityService;
import com.daw.cinema.service.GenreService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MovieEventMapper.class})
public abstract class MovieMapper {

  @Autowired
  private GenreService genreService;
  @Autowired
  private CelebrityService celebrityService;

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "genres", source = "genres", qualifiedByName = "findGenresByName")
  @Mapping(target = "celebrities", source = "celebrities", qualifiedByName = "findCelebritiesByName")
  @Mapping(target = "movieEvents", source = "movieEventDtos")
  public abstract Movie toEntity(MovieDto movieDto);

  @Mapping(target = "genres", source = "genres", qualifiedByName = "extractGenresEnum")
  @Mapping(target = "celebrities", source = "celebrities", qualifiedByName = "extractCelebritiesNames")
  @Mapping(target = "movieEventDtos", source = "movieEvents")
  public abstract MovieDto toDto(Movie movie);

  @Named(value = "extractGenresEnum")
  public List<MovieGenre> extractGenresEnum(List<Genre> genres){
    return genres.stream().map(Genre::getName).toList();
  }

  @Named(value = "findGenresByName")
  public List<Genre> findGenresByName(List<MovieGenre> genres){
    return genres.stream().map(genreService::getGenreByName).toList();
  }

  @Named(value = "extractCelebritiesNames")
  public List<String> extractCelebritiesNames(List<Celebrity> celebrities)
  {
    return celebrities.stream().map(Celebrity::getName).toList();
  }

  @Named(value = "findCelebritiesByName")
  public List<Celebrity> findCelebritiesByName(List<String> celebrities){
    return celebrities.stream().map(celebrityService::getCelebrity).toList();
  }
}
