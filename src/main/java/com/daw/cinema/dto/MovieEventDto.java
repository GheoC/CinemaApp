package com.daw.cinema.dto;

import com.daw.cinema.enums.MovieEventStatus;
import com.daw.cinema.validation.discriminator.OnCreate;
import com.daw.cinema.validation.discriminator.OnUpdate;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
public class MovieEventDto {
  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  private Long id;

  @NotNull
  private Long movieDtoId;

  @Null
  private String movieName;

  @NotNull
  private String room;

  @Future
  @NotNull
  private LocalDateTime playMovieDateTime;

  @NotNull
  private Double price;

  @Null
  private MovieEventStatus status;
}
