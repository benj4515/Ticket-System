package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.Ticket;

import java.util.Date;
import java.util.List;

public interface ITicketDataAccess {

        //this.ticketID = ticketID;
        //this.userID = userID;
        //this.ticketTypeID = ticketTypeID;
        //this.purchaseDate = purchaseDate;

    Ticket createTicket (Ticket newTicket) throws Exception;

    void deleteTicket(Ticket ticketToDelete) throws Exception;

    //void updateTicket(Ticket updatedTicket) throws Exception;

    List<Ticket> getAllTickets() throws Exception;

    public int getTicketID() throws Exception;

    public int getTicketUserID() throws Exception;

    public int getTicketTypeID() throws Exception;

    public Date getPurchaseDate() throws Exception;










}
