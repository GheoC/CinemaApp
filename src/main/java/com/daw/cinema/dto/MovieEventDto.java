package com.daw.cinema.dto;

import com.daw.cinema.enums.MovieEventStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieEventDto {
  private Long id;
  private MovieDto movieDto;
  private String room;
  private LocalDateTime playMovieDateTime;
  private Double price;
  private MovieEventStatus status;
}
