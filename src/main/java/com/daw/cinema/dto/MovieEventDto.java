package com.daw.cinema.dto;

import com.daw.cinema.enums.MovieEventStatus;
import com.daw.cinema.validation.discriminator.OnCreate;
import com.daw.cinema.validation.discriminator.OnUpdate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
public class MovieEventDto {
  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  private Long id;

  private Long movieDtoId;
  private String room;
  private LocalDateTime playMovieDateTime;
  private Double price;
  private MovieEventStatus status;
}
