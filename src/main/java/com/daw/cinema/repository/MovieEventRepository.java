package com.daw.cinema.repository;

import com.daw.cinema.entity.MovieEvent;
import com.daw.cinema.enums.MovieEventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieEventRepository extends JpaRepository<MovieEvent, Long> {
    List<MovieEvent> findAllByPlayMovieDateTimeIsAfter(LocalDateTime localDateTime);

    List<MovieEvent> findByStatusAndMovie_IdAndPlayMovieDateTimeIsAfter(MovieEventStatus status, Long movieId, LocalDateTime localDateTime);

    List<MovieEvent> findByMovie_IdAndPlayMovieDateTimeIsAfter(Long movieId, LocalDateTime localDateTime);
}
