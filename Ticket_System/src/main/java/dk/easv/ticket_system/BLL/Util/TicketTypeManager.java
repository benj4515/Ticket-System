/**
 * Manages ticket type-related business logic in the ticket system.
 * Provides methods for creating, retrieving, and deleting ticket types
 * as well as accessing ticket type properties through a data access layer.
 */
package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.DAL.EventDAO;
import dk.easv.ticket_system.DAL.ITicketTypeDataAccess;
import dk.easv.ticket_system.DAL.TicketTypeDAO;

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
     * Retrieves the event ID based on the event name.
     *
     * @param eventName The name of the event
     * @return The ID of the event
     * @throws Exception If there's an error retrieving the event ID
     */
    public int getEventIDByName(String eventName) throws Exception {
        return dataAccess.getEventIDByName(eventName);
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

    /**
     * Gets the ID of a ticket type.
     *
     * @return The ID of the ticket type
     * @throws Exception If there's an error retrieving the ticket type ID
     */
    public int getTicketTypeID() throws Exception {
        return dataAccess.getTicketTypeID();
    }

    /**
     * Gets the event ID associated with a ticket type.
     *
     * @return The event ID for the ticket type
     * @throws Exception If there's an error retrieving the event ID
     */
    public int getTicketTypeEventID() throws Exception {
        return dataAccess.getTicketTypeEventID();
    }

    /**
     * Gets the price of a ticket.
     *
     * @return The price of the ticket as a BigDecimal
     * @throws Exception If there's an error retrieving the ticket price
     */
    public BigDecimal getTicketPrice() throws Exception {
        return dataAccess.getTicketPrice();
    }

    /**
     * Gets the description of a ticket type.
     *
     * @return The ticket type description
     * @throws Exception If there's an error retrieving the description
     */
    public String getTicketTypeDescription() throws Exception {
        return dataAccess.getTicketTypeDescription();
    }

    /**
     * Gets the number of sold tickets for a ticket type.
     *
     * @return The count of sold tickets
     * @throws Exception If there's an error retrieving the count
     */
    public int getSoldTickets() throws Exception {
        return dataAccess.getSoldTickets();
    }
}