/**
 * Represents a ticket in the ticket system.
 * Contains information about the ticket such as its unique identifier,
 * associated user, ticket type, and when it was purchased.
 */
package dk.easv.ticket_system.BE;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketID;           // Unique identifier for the ticket
    private int userID;             // ID of the user who purchased the ticket
    private int ticketTypeID;       // ID of the ticket type (references ticket category/properties)
    private LocalDateTime purchaseDate; // Date and time when the ticket was purchased

    /**
     * Constructs a ticket with all required information.
     *
     * @param ticketID Unique identifier for the ticket
     * @param userID ID of the user who purchased the ticket
     * @param ticketTypeID ID of the ticket type
     * @param purchaseDate Date and time when the ticket was purchased
     */
    public Ticket(int ticketID, int userID, int ticketTypeID, LocalDateTime purchaseDate) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.ticketTypeID = ticketTypeID;
        this.purchaseDate = purchaseDate;

    }

    /**
     * Gets the ticket's unique identifier.
     * @return The ticket ID
     */
    public int getTicketID() {
        return ticketID;
    }

    /**
     * Sets the ticket's unique identifier.
     * @param ticketID The new ticket ID
     */
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    /**
     * Gets the ID of the user who purchased the ticket.
     * @return The user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the ID of the user who purchased the ticket.
     * @param eventID The new user ID
     */
    public void setUserID(int eventID) {
        this.userID = eventID;
    }

    /**
     * Gets the ticket type ID.
     * @return The ticket type ID
     */
    public int getTicketTypeID() {
        return ticketTypeID;
    }

    /**
     * Sets the ticket type ID.
     * @param ticketTypeID The new ticket type ID
     */
    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    /**
     * Gets the date and time when the ticket was purchased.
     * @return The purchase date and time
     */
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the date and time when the ticket was purchased.
     * @param purchaseDate The new purchase date and time
     */
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}