package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.Ticket;
import dk.easv.ticket_system.BLL.Util.TicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TicketModel {
    private final ObservableList<Ticket> ticketObservableList;
    private final TicketManager ticketManager;

    public TicketModel() throws Exception {
        this.ticketManager = new TicketManager();
        ticketObservableList = FXCollections.observableArrayList();
        ticketObservableList.addAll(ticketManager.getAllTickets());
    }

    public ObservableList<Ticket> getTicketObservableList() {
        return ticketObservableList;
    }

    public void createTicket(Ticket newTicket) throws Exception {
        Ticket t = ticketManager.createTicket(newTicket);
        ticketObservableList.add(t);
    }

    public void deleteTicket(Ticket ticketToDelete) throws Exception {
        ticketManager.deleteTicket(ticketToDelete);
        ticketObservableList.remove(ticketToDelete);
    }

    public List<Ticket> getAllTickets() throws Exception {
        List<Ticket> tickets = new ArrayList<>();

        ticketObservableList.clear();
        ticketObservableList.addAll(tickets);
        return tickets;
    }
}
