package com.daw.cinema.entity;

import com.daw.cinema.enums.MovieStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String trailer;
    private String img;
    private String duration;
    private Boolean is3D;
    private String description;
    private Boolean promoted;
    private Double imdb;
    private String director;
    private LocalDate premierDate;
    private String language;
    private String subtitles;

    @Enumerated(value = EnumType.STRING)
    private MovieStatus status;

    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "movie_celebrity", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "celebrity_id"))
    private List<Celebrity> celebrities;
}
