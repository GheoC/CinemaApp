package com.daw.cinema.dto;

import com.daw.cinema.validation.discriminator.OnCreate;
import com.daw.cinema.validation.discriminator.OnUpdate;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.Duration;

@Data
public class MovieDto {
  @NotNull(groups = OnUpdate.class)
  @Null(groups = OnCreate.class)
  private Long id;

  @NotEmpty
  @Max(value = 150)
  private String name;

  @NotEmpty private String duration;
}
