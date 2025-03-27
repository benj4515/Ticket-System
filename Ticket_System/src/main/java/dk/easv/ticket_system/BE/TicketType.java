package dk.easv.ticket_system.BE;

import java.math.BigDecimal;

public class TicketType {
    private int ticketTypeID;
    private int eventID;
    private BigDecimal ticketPrice;
    private String ticketDescription;

    private TicketType(int ticketTypeID, int eventID, BigDecimal ticketPrice, String ticketDescription) {
        this.ticketTypeID = ticketTypeID;
        this.eventID = eventID;
        this.ticketPrice = ticketPrice;
        this.ticketDescription = ticketDescription;
    }

    private int getTicketTypeID() {
        return ticketTypeID;
    }

    private void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    private int getEventID() {
        return eventID;
    }

    private void setEventID(int eventID) {
        this.eventID = eventID;
    }

    private BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    private void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    private String getTicketDescription() {
        return ticketDescription;
    }

    private void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }
}
