package com.daw.cinema.dto;

import com.daw.cinema.enums.MovieStatus;
import com.daw.cinema.enums.MovieGenre;
import com.daw.cinema.validation.discriminator.OnCreate;
import com.daw.cinema.validation.discriminator.OnUpdate;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class MovieDto {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 150)
    private String name;

    @NotEmpty
    @Size(max = 10)
    private String duration;

    @NotEmpty
    @Size(max = 1000)
    private String trailer;

    @NotEmpty
    @Size(max = 100)
    private String img;

    @NotNull
    private Boolean is3D;

    @NotEmpty
    @Size(max = 2000)
    private String description;

    @NotNull
    private Boolean promoted;

    @NotNull
    private Double imdb;

    @NotEmpty
    @Size(max = 150)
    private String director;

    @NotNull
    private MovieStatus status;

    @NotNull
    private LocalDate premierDate;

    @NotEmpty
    private String language;

    @NotEmpty
    private String subtitles;

    private List<MovieGenre> genres;

    private List<String> celebrities;
}
