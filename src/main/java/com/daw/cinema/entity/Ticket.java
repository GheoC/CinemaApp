package com.daw.cinema.entity;

import com.daw.cinema.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movieEventId")
    private MovieEvent movieEvent;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private double priceCharged;

    @Enumerated(value = EnumType.STRING)
    private TicketStatus status;
}
