package com.daw.cinema.controller;

import com.daw.cinema.dto.MovieDto;
import com.daw.cinema.mapper.MovieMapper;
import com.daw.cinema.service.MovieService;
import com.daw.cinema.validation.discriminator.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost/3000")
public class MovieController {
  private final MovieService movieService;
  private final MovieMapper movieMapper;

  @GetMapping("/api/v1/movies")
  public List<MovieDto> getAllMovies() {
    return movieService.getAllMovies().stream().map(movieMapper::toDto).toList();
  }

  @GetMapping("/api/v1/movies/{id}")
  public MovieDto getMovie(@PathVariable Long id){
    return movieMapper.toDto(movieService.getMovie(id));
  }

  @PostMapping("api/v1/movies")
  @PreAuthorize("hasRole('ADMIN')")
  public MovieDto addMovie(@RequestBody @Validated(OnCreate.class) MovieDto movieDto) {
    return movieMapper.toDto(movieService.addMovie(movieMapper.toEntity(movieDto)));
  }

  @DeleteMapping("api/v1/movies/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteMovie(@PathVariable Long id){
    movieService.delete(id);
  }
}
