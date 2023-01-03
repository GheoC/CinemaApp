package com.daw.cinema.repository;

import com.daw.cinema.entity.Genre;
import com.daw.cinema.enums.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenresRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(MovieGenre name);
}
