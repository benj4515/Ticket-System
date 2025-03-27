package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.Ticket;
import dk.easv.ticket_system.DAL.ITicketDataAccess;
import dk.easv.ticket_system.DAL.TicketDAO;

import java.io.IOException;
import java.util.List;

public class TicketManager {
    private final ITicketDataAccess dataAccess;

    public TicketManager() throws IOException {
    dataAccess = new TicketDAO();
    }

    public Ticket createTicket(Ticket newTicket) throws Exception {
        return dataAccess.createTicket(newTicket);
    }

    public List<Ticket> getAllTickets () throws Exception {
        return dataAccess.getAllTickets();
    }

    public void deleteTicket(Ticket ticketToDelete) throws Exception {
        dataAccess.deleteTicket(ticketToDelete);
    }

}
