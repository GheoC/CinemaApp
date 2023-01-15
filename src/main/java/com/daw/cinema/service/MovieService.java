package com.daw.cinema.service;

import com.daw.cinema.entity.Movie;
import com.daw.cinema.enums.MovieStatus;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
  private final MovieRepository movieRepository;
  private final MoviePictureService moviePictureService;

  public List<Movie> getMovies(MovieStatus status) {
    if (status !=null){
      return movieRepository.findByStatus(status);
    }
    return movieRepository.findAll();
  }

  public Movie addMovie(Movie movie) {
    return movieRepository.save(movie);
  }

  public Movie getMovie(Long id) {
    return movieRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Movie Not found"));
  }

  @Transactional
  public void delete(Long id) {
    Movie movieToDelete = getMovie(id);
    movieRepository.delete(movieToDelete);
    moviePictureService.deleteByImageName(movieToDelete.getImg());
  }

  public void switchStatus(Long id) {
    Movie movie = getMovie(id);
    movie.setStatus(movie.getStatus() == MovieStatus.PLAYING ? MovieStatus.CANCELED : MovieStatus.PLAYING);
    movieRepository.save(movie);
  }
}
