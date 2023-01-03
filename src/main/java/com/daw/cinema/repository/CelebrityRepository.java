package com.daw.cinema.repository;

import com.daw.cinema.entity.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {

    Optional<Celebrity> findByName(String name);
}
