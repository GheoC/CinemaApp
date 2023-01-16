package com.daw.cinema.repository;

import com.daw.cinema.entity.MovieEvent;
import com.daw.cinema.enums.MovieEventStatus;
import com.daw.cinema.enums.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieEventRepository extends JpaRepository<MovieEvent, Long> {
    List<MovieEvent> findByStatusAndPlayMovieDateTimeIsAfterAndMovie_Status(MovieEventStatus status, LocalDateTime playMovieDateTime, MovieStatus movieStatus);
    List<MovieEvent> findByPlayMovieDateTimeIsAfter(LocalDateTime playMovieDateTime);

    List<MovieEvent> findByStatusAndMovie_IdAndPlayMovieDateTimeIsAfter(MovieEventStatus status, Long movieId, LocalDateTime playMovieDateTime);

    List<MovieEvent> findByMovie_IdAndPlayMovieDateTimeIsAfter(Long movieId, LocalDateTime playMovieDateTime);
}
