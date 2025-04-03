package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.DAL.EventDAO;
import dk.easv.ticket_system.DAL.ITicketTypeDataAccess;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class TicketTypeManager {
    private final ITicketTypeDataAccess dataAccess;

    public TicketTypeManager() throws IOException {
        dataAccess = new EventDAO();
    }

    public int getEventIDByName(String eventName) throws Exception {
        return dataAccess.getEventIDByName(eventName);
    }

    public TicketType createTicketType(TicketType newTicketType) throws Exception {
        return dataAccess.createTicketType(newTicketType);
    }

    public void deleteTicketType(TicketType ticketTypeToBeDeleted) throws Exception {
        dataAccess.deleteTicketType(ticketTypeToBeDeleted);
    }

    public List<TicketType> getAllTicketTypes() throws Exception {
        return dataAccess.getAllTicketTypes();
    }

    public int getTicketTypeID() throws Exception {
        return dataAccess.getTicketTypeID();
    }

    public int getTicketTypeEventID() throws Exception {
        return dataAccess.getTicketTypeEventID();
    }

    public BigDecimal getTicketPrice() throws Exception {
        return dataAccess.getTicketPrice();
    }

    public String getTicketTypeDescription() throws Exception {
        return dataAccess.getTicketTypeDescription();
    }

    public int getSoldTickets() throws Exception {
        return dataAccess.getSoldTickets();
    }
}
