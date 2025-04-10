/**
 * Represents a ticket type in the ticket system.
 * Contains information about different categories of tickets available for events,
 * including pricing, description, and sales tracking.
 */
package dk.easv.ticket_system.BE;


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
     * Gets the event ID this ticket type belongs to.
     * @return The event ID
     */
    public int getEventID() {
        return eventID;
    }


    /**
     * Gets the ticket price.
     * @return The ticket price as a double
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Gets the ticket description.
     * @return The ticket description
     */
    public String getTicketDescription() {
        return ticketDescription;
    }


    /**
     * Gets the color associated with this ticket type.
     * @return The ticket color as a string (likely a hex code)
     */
    public String getTicketTypeColor() {
        return ticketColor;
    }


    /**
     * Gets the ticket name.
     * @return The ticket name
     */
    public String getTicketName() {
        return ticketName;
    }



}