package com.daw.cinema.service;

import com.daw.cinema.entity.Genre;
import com.daw.cinema.enums.MovieGenre;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.repository.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenresRepository genresRepository;

    public Genre getGenreByName(MovieGenre name) {
        return genresRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Genre NOT Found"));
    }
}
