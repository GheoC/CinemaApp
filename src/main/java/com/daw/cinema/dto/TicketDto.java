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
    private String room;
    @Null
    private byte[] picture;

    @Null(groups = OnCreate.class)
    private TicketStatus status;

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", movieEventId=" + movieEventId +
                ", priceCharged=" + priceCharged +
                ", movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieImg='" + movieImg + '\'' +
                ", playMovieDateTime=" + playMovieDateTime +
                ", room='" + room + '\'' +
                ", status=" + status +
                '}';
    }
}
