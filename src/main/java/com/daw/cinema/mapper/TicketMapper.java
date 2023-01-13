package com.daw.cinema.mapper;

import com.daw.cinema.dto.TicketDto;
import com.daw.cinema.entity.Ticket;
import com.daw.cinema.service.MoviePictureService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TicketMapper {
    @Autowired
    private MoviePictureService moviePictureService;

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "movieEventId", source = "movieEvent.id")
    @Mapping(target = "movieId", source = "movieEvent.movie.id")
    @Mapping(target = "movieName", source = "movieEvent.movie.name")
    @Mapping(target = "movieImg", source = "movieEvent.movie.img")
    @Mapping(target = "playMovieDateTime", source = "movieEvent.playMovieDateTime")
    @Mapping(target = "room", source = "movieEvent.room")
    @Mapping(target = "picture", source = "movieEvent.movie.img", qualifiedByName = "getPictureForMovie")
    public abstract TicketDto toDto(Ticket ticket);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "movieEvent.id", source = "movieEventId")
    @Mapping(target = "status", ignore = true)
    public abstract Ticket toEntity(TicketDto ticketDto);

    @Named(value = "getPictureForMovie")
    public byte[] getPictureForMovie(String img) {
        return moviePictureService.findImageByName(img);
    }
}
