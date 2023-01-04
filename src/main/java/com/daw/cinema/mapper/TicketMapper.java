package com.daw.cinema.mapper;

import com.daw.cinema.dto.TicketDto;
import com.daw.cinema.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "movieEventId", source = "movieEvent.id")
    @Mapping(target = "movieId", source = "movieEvent.movie.id")
    @Mapping(target = "playMovieDateTime", source = "movieEvent.playMovieDateTime")
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "movieEvent.id", source = "movieEventId")
    @Mapping(target = "status", ignore = true)
    Ticket toEntity(TicketDto ticketDto);
}
