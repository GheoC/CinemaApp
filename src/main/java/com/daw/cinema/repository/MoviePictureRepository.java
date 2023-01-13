package com.daw.cinema.repository;

import com.daw.cinema.entity.MoviePicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoviePictureRepository extends JpaRepository<MoviePicture, Long> {

    Optional<MoviePicture> findByName(String name);

    void deleteByName(String name);
}
