package com.daw.cinema.spock

import com.daw.cinema.entity.Movie
import com.daw.cinema.entity.MovieEvent
import com.daw.cinema.entity.Ticket
import com.daw.cinema.entity.User
import com.daw.cinema.enums.TicketStatus
import com.daw.cinema.exception.exceptions.ResourceNotFoundException
import com.daw.cinema.repository.TicketRepository
import com.daw.cinema.service.MovieEventService
import com.daw.cinema.service.TicketService
import com.daw.cinema.service.UserService
import spock.lang.Specification
import spock.lang.Subject

class TicketServiceTests extends Specification {

    def ticketRepository = Mock(TicketRepository.class)
    def userService = Mock(UserService.class)
    def movieEventService = Mock(MovieEventService.class)

    @Subject
    def ticketService = new TicketService(ticketRepository, userService, movieEventService)

    def 'givenCorrectData_whenTicketIsCreated_thenReturnTicket_successfully' () {
        given:
            def movieEventFromDB = new MovieEvent(id: 1, movie: new Movie(name: "Avatar"))
            def userFromDB = new User(id: 1, firstName: 'Gheorghe', lastName: 'Coanta', email: 'gheorghe.coanta@gmail.com' )
            def newTicket = new Ticket(id: 1, movieEvent: new MovieEvent(id: 1), user: new User(id: 1), priceCharged: 25, status: TicketStatus.ORDERED)

        when:
            def result =ticketService.createTicket(newTicket)
        then:
            1 * userService.getUser(1) >> userFromDB
            1 * movieEventService.getMovieEvent(1) >> movieEventFromDB
            1 * ticketRepository.save(newTicket) >> newTicket
            result
            result.user.firstName == 'Gheorghe'
            result.user.lastName == 'Coanta'
            result.user.email == 'gheorghe.coanta@gmail.com'
            result.movieEvent.movie.name == 'Avatar'
    }

    def 'givenNonExistingUser_whenTicketIsCreated_thenExceptionIsThrown_unsuccessfully' () {
        given:
            def newTicket = new Ticket(id: 1, movieEvent: new MovieEvent(id: 1), user: new User(id: 1), priceCharged: 25, status: TicketStatus.ORDERED)
        when:
            ticketService.createTicket(newTicket)
        then:
            1 * userService.getUser(1) >> { throw new ResourceNotFoundException("User NOT found") }
            0 * _
            thrown(ResourceNotFoundException.class)
    }

    def 'givenNonExistingMovieEvent_whenTicketIsCreated_thenExceptionIsThrown_unsuccessfully' () {
        given:
            def userFromDB = new User(id: 1, firstName: 'Gheorghe', lastName: 'Coanta', email: 'gheorghe.coanta@gmail.com' )
            def newTicket = new Ticket(id: 1, movieEvent: new MovieEvent(id: 1), user: new User(id: 1), priceCharged: 25, status: TicketStatus.ORDERED)
        when:
            ticketService.createTicket(newTicket)
        then:
            1 * userService.getUser(1) >> userFromDB
            1 * movieEventService.getMovieEvent(1) >> { throw new ResourceNotFoundException('Event Not found')}
            0 * _
            thrown(ResourceNotFoundException.class)
    }

    def 'givenTicketId_whenTicketIsCanceled_thenTicketStatusIsCanceled_successfully' () {
        given:
            def ticketFromDB = new Ticket(id: 1, status: TicketStatus.ORDERED)
        when:
            ticketService.cancelTicket(1)
        then:
            1 * ticketRepository.findById(1) >> Optional.of(ticketFromDB)
            1 * ticketRepository.save(ticketFromDB) >> ticketFromDB
            ticketFromDB.status == TicketStatus.CANCELED
    }

    def 'givenTicketNotFound_whenTicketIsCanceled_thenExceptionIsThrown_unsuccessfully' () {
        when:
            ticketService.cancelTicket(1)
        then:
            1 * ticketRepository.findById(1) >> Optional.empty()
            0 * _
            thrown(ResourceNotFoundException.class)
    }
}
