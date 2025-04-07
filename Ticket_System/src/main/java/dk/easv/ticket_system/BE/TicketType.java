package dk.easv.ticket_system.BE;

import java.math.BigDecimal;

public class TicketType {
    private int ticketTypeID;
    private String TicketName;
    private int eventID;
    private double ticketPrice;
    private String ticketDescription;
    private int ticketsSold;
    private String ticketColor;
    private String ticketName;


    public TicketType(int ticketTypeID, int eventID, float ticketPrice, String ticketDescription, int ticketsSold, String ticketColor) {
        this.ticketTypeID = ticketTypeID;
        this.eventID = eventID;
        this.ticketPrice = ticketPrice;
        this.ticketDescription = ticketDescription;
        this.ticketsSold = ticketsSold;
        this.ticketColor = ticketColor;
    }





    public TicketType(int ticketTypeID, int eventID, float ticketPrice, String ticketDescription, int ticketsSold) {

        this.ticketTypeID = ticketTypeID;
        this.eventID = eventID;
        this.ticketPrice = ticketPrice;
        this.ticketDescription = ticketDescription;
        this.ticketsSold = ticketsSold;
        this.ticketColor = ticketColor;
    }


    public TicketType( String ticketName, String ticketDescription, String ticketPrice) {
        this.TicketName = ticketName;
        this.ticketDescription = ticketDescription;
        this.ticketPrice = Float.parseFloat(ticketPrice);
    }




    public TicketType(String ticketName, String ticketDescription, double ticketPrice) {
        this.ticketDescription = ticketDescription;
        this.ticketPrice = ticketPrice;
        this.ticketName = ticketName;
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

    public double getTicketPrice() {
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


    public String getTicketTypeColor() {
        return ticketColor;
    }

    public void setTicketPrice ( double ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    public String getTicketName () {
        return ticketName;
    }

    public void setTicketName (String ticketName){
        this.ticketName = ticketName;
    }


    public TicketType() {
    }
}

