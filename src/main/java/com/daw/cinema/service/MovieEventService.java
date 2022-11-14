package com.daw.cinema.service;

import com.daw.cinema.entity.Movie;
import com.daw.cinema.entity.MovieEvent;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.repository.MovieEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieEventService {
  private final MovieEventRepository movieEventRepository;
  private final MovieService movieService;

  public MovieEvent addEvent(MovieEvent movieEvent) {
    Movie movie = movieService.getMovie(movieEvent.getMovie().getId());
    movieEvent.setMovie(movie);
    return movieEventRepository.save(movieEvent);
  }

  public List<MovieEvent> getAllEvents() {
    return movieEventRepository.findAll();
  }

  public MovieEvent getMovieEvent(Long id) {
    return movieEventRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Event Not found"));
  }

  public List<MovieEvent> getAllEventsForMovie(Long movieId) {
    return movieEventRepository.findByMovie_Id(movieId);
  }
}
