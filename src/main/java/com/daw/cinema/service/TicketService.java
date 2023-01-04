package com.daw.cinema.service;

import com.daw.cinema.entity.MovieEvent;
import com.daw.cinema.entity.Ticket;
import com.daw.cinema.entity.User;
import com.daw.cinema.enums.TicketStatus;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final MovieEventService movieEventService;

    public Ticket createTicket(Ticket ticket) {
        User userFromDB = userService.getUser(ticket.getUser().getId());
        MovieEvent movieEventFromDB = movieEventService.getMovieEvent(ticket.getMovieEvent().getId());
        ticket.setUser(userFromDB);
        ticket.setMovieEvent(movieEventFromDB);
        ticket.setStatus(TicketStatus.ORDERED);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllTicketsForUser(Long id) {
        return ticketRepository.findByUser_Id(id);
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket Not Found"));
    }

    public void cancelTicket(Long id) {
        Ticket ticketFromDB = getTicketById(id);
        ticketFromDB.setStatus(TicketStatus.CANCELED);
        ticketRepository.save(ticketFromDB);
    }
}
