package com.daw.cinema.controller;

import com.daw.cinema.dto.MovieEventDto;
import com.daw.cinema.mapper.MovieEventMapper;
import com.daw.cinema.service.MovieEventService;
import com.daw.cinema.validation.discriminator.OnCreate;
import com.daw.cinema.validation.discriminator.ValidEachMovieEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost/3000")
@Validated
public class MovieEventController {
  private final MovieEventService movieEventService;
  private final MovieEventMapper movieEventMapper;

  @PostMapping("/api/v1/movie-events")
  @PreAuthorize("hasRole('ADMIN')")
  public MovieEventDto addMovieEvent(@RequestBody @Validated(OnCreate.class) MovieEventDto movieEventDto) {
    return movieEventMapper.toDto(
        movieEventService.addEvent(movieEventMapper.toEntity(movieEventDto)));
  }

  @PostMapping("/api/v1/movie-events/list")
  @PreAuthorize("hasRole('ADMIN')")
  public List<MovieEventDto> addMovieEvents(@RequestBody @ValidEachMovieEvent List<MovieEventDto> movieEventDtoList){
    return movieEventService.addEvents(movieEventDtoList.stream().map(movieEventMapper::toEntity).toList())
            .stream().map(movieEventMapper::toDto).toList();
  }

  @GetMapping("/api/v1/movie-events")
  public List<MovieEventDto> getMovieEvents() {
    return movieEventService.getAllEvents().stream().map(movieEventMapper::toDto).toList();
  }

  @GetMapping("/api/v1/movie-events/{id}")
  public MovieEventDto getMovieEvent(@PathVariable Long id) {
    return movieEventMapper.toDto(movieEventService.getMovieEvent(id));
  }

  @GetMapping("/api/v1/movie-events/movie/{id}")
  public List<MovieEventDto> getAllEventsForMovie(@PathVariable Long id) {
    return movieEventService.getAllFutureEventsForMovie(id).stream()
        .map(movieEventMapper::toDto)
        .toList();
  }

  @GetMapping("/api/v1/movie-events/future")
  public List<MovieEventDto> getFutureMovieEvents(){
    return movieEventService.getAllFutureEvents().stream().map(movieEventMapper::toDto).toList();
  }
}
