package com.daw.cinema.dto;

import com.daw.cinema.enums.MovieStatus;
import com.daw.cinema.enums.MovieType;
import com.daw.cinema.validation.discriminator.OnCreate;
import com.daw.cinema.validation.discriminator.OnUpdate;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MovieDto {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 150)
    private String name;

    @NotEmpty
    @Size(max = 4)
    private String duration;
    @NotEmpty
    @Size(max = 1000)
    private String trailer;
    @NotEmpty
    @Size(max = 150)
    private String director;
    @NotNull
    private MovieType type;
    @NotNull
    private MovieStatus status;
}
