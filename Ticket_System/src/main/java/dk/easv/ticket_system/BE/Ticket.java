package dk.easv.ticket_system.BE;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketID;
    private int userID;
    private int ticketTypeID;
    private LocalDateTime purchaseDate;

    public Ticket(int ticketID, int userID, int ticketTypeID, LocalDateTime purchaseDate) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.ticketTypeID = ticketTypeID;
        this.purchaseDate = purchaseDate;

    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int eventID) {
        this.userID = eventID;
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
