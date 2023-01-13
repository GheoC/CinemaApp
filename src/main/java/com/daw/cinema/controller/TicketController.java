package com.daw.cinema.controller;

import com.daw.cinema.dto.TicketDto;
import com.daw.cinema.mapper.TicketMapper;
import com.daw.cinema.service.TicketService;
import com.daw.cinema.validation.discriminator.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @PostMapping("/api/v1/tickets")
    public TicketDto buyTicket(@RequestBody @Validated(OnCreate.class) TicketDto ticketDto) {
        return ticketMapper.toDto(ticketService.createTicket(ticketMapper.toEntity(ticketDto)));
    }

    @GetMapping("/api/v1/tickets")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TicketDto> getTickets() {
        return ticketService.getAll().stream().map(ticketMapper::toDto).toList();
    }

    @GetMapping("/api/v1/tickets/user/{id}")
    public List<TicketDto> getTicketsBoughtByUser(@PathVariable Long id) {
        return ticketService.getAllTicketsForUser(id).stream().map(ticketMapper::toDto).toList();
    }

    @PutMapping("/api/v1/tickets/{id}")
    public void cancelTicket(@PathVariable Long id) {
        ticketService.cancelTicket(id);
    }
}
