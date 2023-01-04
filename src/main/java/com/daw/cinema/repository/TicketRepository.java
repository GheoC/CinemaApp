package com.daw.cinema.repository;


import com.daw.cinema.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>
{
    List<Ticket> findByUser_Id(Long userId);
}
