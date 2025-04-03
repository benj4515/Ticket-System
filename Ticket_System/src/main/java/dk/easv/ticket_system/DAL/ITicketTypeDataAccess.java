package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BE.User;

import java.math.BigDecimal;
import java.util.List;

public interface ITicketTypeDataAccess {



    TicketType createTicketType(TicketType newTicketType) throws Exception;

    void deleteTicketType(TicketType ticketTypeToBeDeleted) throws Exception;

    List<TicketType> getAllTicketTypes() throws Exception;

    public int getTicketTypeID() throws Exception;

    public int getTicketTypeEventID() throws Exception;

    public BigDecimal getTicketPrice() throws Exception;

    public String getTicketTypeDescription() throws Exception;

    public int getSoldTickets() throws Exception;

    public int getEventIDByName(String eventName) throws Exception;
<<<<<<< Updated upstream
=======


<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
