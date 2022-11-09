package com.daw.cinema.repository;

import com.daw.cinema.entity.MovieEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieEventRepository extends JpaRepository<MovieEvent, Long> {}
