package com.daw.cinema.dto;

import com.daw.cinema.enums.TicketStatus;
import com.daw.cinema.validation.discriminator.OnCreate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDto {
    @Null(groups = OnCreate.class)
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long movieEventId;
    @NotNull
    private Double priceCharged;

    @Null
    private Long movieId;
    @Null
    private String movieName;
    @Null
    private String movieImg;
    @Null
    private LocalDateTime playMovieDateTime;
    @Null
    protected String room;

    @Null(groups = OnCreate.class)
    private TicketStatus status;
}
