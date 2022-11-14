package com.daw.cinema.repository;

import com.daw.cinema.entity.MovieEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieEventRepository extends JpaRepository<MovieEvent, Long>
{
    List<MovieEvent> findByMovie_Id(Long movieId);
}
