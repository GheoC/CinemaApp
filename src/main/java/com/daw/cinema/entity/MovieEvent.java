package com.daw.cinema.entity;

import com.daw.cinema.enums.MovieEventStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MovieEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "movieId")
  private Movie movie;

  private String room;

  @Column(name = "playmovie_datetime")
  private LocalDateTime playMovieDateTime;

  private Double price;

  @Enumerated(value = EnumType.STRING)
  private MovieEventStatus status;
}
