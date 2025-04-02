package dk.easv.ticket_system.BE;

import java.math.BigDecimal;

public class TicketType {
    private int ticketTypeID;
    private int eventID;
    private float ticketPrice;
    private String ticketDescription;
    private int ticketsSold;

    public TicketType(int ticketTypeID, int eventID, float ticketPrice, String ticketDescription, int ticketsSold) {
        this.ticketTypeID = ticketTypeID;
        this.eventID = eventID;
        this.ticketPrice = ticketPrice;
        this.ticketDescription = ticketDescription;
        this.ticketsSold = ticketsSold;
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public int getTicketsSold() {return ticketsSold;}

    public void setTicketsSold(int ticketsSold) {this.ticketsSold = ticketsSold;}
}
