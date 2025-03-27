package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.Ticket;

import java.util.List;

public interface ITicketDataAccess {

    Ticket createTicket (Ticket newTicket) throws Exception;

    void deleteTicket(Ticket ticketToDelete) throws Exception;

    //void updateTicket(Ticket updatedTicket) throws Exception;

    List<Ticket> getAllTickets() throws Exception;
}
