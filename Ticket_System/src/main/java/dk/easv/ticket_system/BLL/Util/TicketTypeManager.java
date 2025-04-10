/**
 * Manages ticket type-related business logic in the ticket system.
 * Provides methods for creating, retrieving, and deleting ticket types
 * as well as accessing ticket type properties through a data access layer.
 */
package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.DAL.EventDAO;
import dk.easv.ticket_system.DAL.ITicketTypeDataAccess;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class TicketTypeManager {
    private final ITicketTypeDataAccess dataAccess;    // Data access object for ticket type operations

    /**
     * Constructs a TicketTypeManager with an EventDAO instance.
     * Note: Uses EventDAO instead of TicketTypeDAO as the implementation.
     *
     * @throws IOException If there's an error initializing the data access layer
     */
    public TicketTypeManager() throws IOException {
        dataAccess = new EventDAO();
    }



    /**
     * Creates a new ticket type in the system.
     *
     * @param newTicketType The ticket type object to create
     * @return The created ticket type with assigned ID
     * @throws Exception If there's an error during ticket type creation
     */
    public TicketType createTicketType(TicketType newTicketType) throws Exception {
        return dataAccess.createTicketType(newTicketType);
    }

    /**
     * Deletes an existing ticket type.
     *
     * @param ticketTypeToBeDeleted The ticket type to be deleted
     * @throws Exception If there's an error during ticket type deletion
     */
    public void deleteTicketType(TicketType ticketTypeToBeDeleted) throws Exception {
        dataAccess.deleteTicketType(ticketTypeToBeDeleted);
    }

    /**
     * Retrieves all ticket types in the system.
     *
     * @return A list of all ticket types
     * @throws Exception If there's an error retrieving ticket types
     */
    public List<TicketType> getAllTicketTypes() throws Exception {
        return dataAccess.getAllTicketTypes();
    }

}