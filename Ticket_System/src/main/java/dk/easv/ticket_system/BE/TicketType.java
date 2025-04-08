/**
 * Represents a ticket type in the ticket system.
 * Contains information about different categories of tickets available for events,
 * including pricing, description, and sales tracking.
 */
package dk.easv.ticket_system.BE;

import java.math.BigDecimal;

public class TicketType {
    private int ticketTypeID;       // Unique identifier for the ticket type
    private String TicketName;      // Name of the ticket type (alternative field, possibly redundant)
    private int eventID;            // ID of the event this ticket type belongs to
    private double ticketPrice;     // Price of this ticket type
    private String ticketDescription; // Description of this ticket type
    private int ticketsSold;        // Number of tickets sold for this ticket type
    private String ticketColor;     // Color associated with this ticket type (for UI display)
    private String ticketName;      // Name of the ticket type

    /**
     * Constructs a complete ticket type with all properties including color.
     *
     * @param ticketTypeID Unique identifier for the ticket type
     * @param eventID ID of the event this ticket type belongs to
     * @param ticketPrice Price of the ticket
     * @param ticketDescription Description of the ticket type
     * @param ticketsSold Number of tickets sold
     * @param ticketColor Color associated with this ticket type
     */
    public TicketType(int ticketTypeID, int eventID, float ticketPrice, String ticketDescription, int ticketsSold, String ticketColor) {
        this.ticketTypeID = ticketTypeID;
        this.eventID = eventID;
        this.ticketPrice = ticketPrice;
        this.ticketDescription = ticketDescription;
        this.ticketsSold = ticketsSold;
        this.ticketColor = ticketColor;
    }

    /**
     * Constructs a ticket type without specifying a color.
     *
     * @param ticketTypeID Unique identifier for the ticket type
     * @param eventID ID of the event this ticket type belongs to
     * @param ticketPrice Price of the ticket
     * @param ticketDescription Description of the ticket type
     * @param ticketsSold Number of tickets sold
     */
    public TicketType(int ticketTypeID, int eventID, float ticketPrice, String ticketDescription, int ticketsSold) {
        this.ticketTypeID = ticketTypeID;
        this.eventID = eventID;
        this.ticketPrice = ticketPrice;
        this.ticketDescription = ticketDescription;
        this.ticketsSold = ticketsSold;
        this.ticketColor = ticketColor;
    }

    /**
     * Constructs a simplified ticket type with name, description and price as a string.
     * Used when creating new ticket types before database insertion.
     *
     * @param ticketName Name of the ticket type
     * @param ticketDescription Description of the ticket type
     * @param ticketPrice Price of the ticket as a string (will be parsed to float)
     */
    public TicketType(String ticketName, String ticketDescription, String ticketPrice) {
        this.TicketName = ticketName;
        this.ticketDescription = ticketDescription;
        this.ticketPrice = Float.parseFloat(ticketPrice);
    }

    /**
     * Constructs a simplified ticket type with name, description and price as a double.
     * Used when creating new ticket types before database insertion.
     *
     * @param ticketName Name of the ticket type
     * @param ticketDescription Description of the ticket type
     * @param ticketPrice Price of the ticket as a double
     */
    public TicketType(String ticketName, String ticketDescription, double ticketPrice) {
        this.ticketDescription = ticketDescription;
        this.ticketPrice = ticketPrice;
        this.ticketName = ticketName;
    }

    /**
     * Gets the ticket type's unique identifier.
     * @return The ticket type ID
     */
    public int getTicketTypeID() {
        return ticketTypeID;
    }

    /**
     * Sets the ticket type's unique identifier.
     * @param ticketTypeID The new ticket type ID
     */
    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    /**
     * Gets the event ID this ticket type belongs to.
     * @return The event ID
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * Sets the event ID this ticket type belongs to.
     * @param eventID The new event ID
     */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * Gets the ticket price.
     * @return The ticket price as a double
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets the ticket price (float version).
     * @param ticketPrice The new ticket price as a float
     */
    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Gets the ticket description.
     * @return The ticket description
     */
    public String getTicketDescription() {
        return ticketDescription;
    }

    /**
     * Sets the ticket description.
     * @param ticketDescription The new ticket description
     */
    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    /**
     * Gets the number of tickets sold for this ticket type.
     * @return The number of tickets sold
     */
    public int getTicketsSold() {return ticketsSold;}

    /**
     * Sets the number of tickets sold for this ticket type.
     * @param ticketsSold The new number of tickets sold
     */
    public void setTicketsSold(int ticketsSold) {this.ticketsSold = ticketsSold;}

    /**
     * Gets the color associated with this ticket type.
     * @return The ticket color as a string (likely a hex code)
     */
    public String getTicketTypeColor() {
        return ticketColor;
    }

    /**
     * Sets the ticket price (double version).
     * @param ticketPrice The new ticket price as a double
     */
    public void setTicketPrice(double ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    /**
     * Gets the ticket name.
     * @return The ticket name
     */
    public String getTicketName() {
        return ticketName;
    }

    /**
     * Sets the ticket name.
     * @param ticketName The new ticket name
     */
    public void setTicketName(String ticketName){
        this.ticketName = ticketName;
    }

    /**
     * Default constructor for the TicketType class.
     * Creates an empty ticket type object.
     */
    public TicketType() {
    }
}