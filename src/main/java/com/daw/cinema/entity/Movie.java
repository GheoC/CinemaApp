package com.daw.cinema.entity;

import com.daw.cinema.enums.MovieStatus;
import com.daw.cinema.enums.MovieType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String duration;
    private String trailer;
    private String director;
    @Enumerated(value = EnumType.STRING)
    private MovieType type;
    @Enumerated(value = EnumType.STRING)
    private MovieStatus status;
}
