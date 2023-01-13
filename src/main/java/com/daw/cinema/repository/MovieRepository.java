package com.daw.cinema.repository;

import com.daw.cinema.entity.Movie;
import com.daw.cinema.enums.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByStatus(MovieStatus status);
}
