package com.daw.cinema.service;

import com.daw.cinema.entity.Movie;
import com.daw.cinema.entity.MovieEvent;
import com.daw.cinema.enums.MovieEventStatus;
import com.daw.cinema.enums.MovieStatus;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.repository.MovieEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieEventService {
  private final MovieEventRepository movieEventRepository;
  private final MovieService movieService;

  public MovieEvent addEvent(MovieEvent movieEvent) {
    Movie movie = movieService.getMovie(movieEvent.getMovie().getId());
    movieEvent.setMovie(movie);
    movieEvent.setStatus(MovieEventStatus.ACTIVE);
    return movieEventRepository.save(movieEvent);
  }

  @Transactional
  public List<MovieEvent> addEvents(List<MovieEvent> movieEvents) {
    return movieEvents.stream().map(this::addEvent).toList();
  }

  public MovieEvent getMovieEvent(Long id) {
    return movieEventRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Event Not found"));
  }

  public List<MovieEvent> getAllFutureEventsForMovie(Long movieId, MovieEventStatus status) {
    if (status == null){
      return movieEventRepository.findByMovie_IdAndPlayMovieDateTimeIsAfter(movieId, LocalDateTime.now());
    }
    return movieEventRepository.findByStatusAndMovie_IdAndPlayMovieDateTimeIsAfter(status, movieId, LocalDateTime.now());
  }

  public List<MovieEvent> getAllFutureEvents(MovieEventStatus status){
    if (status!= null) {
      return movieEventRepository.findByStatusAndPlayMovieDateTimeIsAfterAndMovie_Status(status, LocalDateTime.now(), MovieStatus.PLAYING);
    }
    return movieEventRepository.findByPlayMovieDateTimeIsAfter(LocalDateTime.now());
  }

  public void switchStatus(Long id) {
    MovieEvent movieEvent = getMovieEvent(id);
    movieEvent.setStatus(movieEvent.getStatus() == MovieEventStatus.ACTIVE ? MovieEventStatus.CANCELED : MovieEventStatus.ACTIVE);
    movieEventRepository.save(movieEvent);
  }
}
