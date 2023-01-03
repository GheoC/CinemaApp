package com.daw.cinema.repository;

import com.daw.cinema.entity.MovieEvent;
import com.daw.cinema.enums.MovieEventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieEventRepository extends JpaRepository<MovieEvent, Long> {
    List<MovieEvent> findByStatusAndMovie_Id(MovieEventStatus status, Long movieId);
}
