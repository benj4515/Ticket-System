/**
 * Model class for managing Tickets in the ticket system.
 * Acts as an intermediary between the UI components and the business logic layer.
 * Maintains an observable collection of tickets for UI binding and delegates
 * operations to the TicketManager business logic class.
 */
package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.Ticket;
import dk.easv.ticket_system.BLL.Util.TicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TicketModel {
    /**
     * Observable collection of Ticket objects for UI binding.
     * Allows JavaFX UI components to automatically update when the collection changes.
     */
    private final ObservableList<Ticket> ticketObservableList;

    /**
     * Business logic manager for ticket operations.
     * Handles data processing and persistence operations for tickets.
     */
    private final TicketManager ticketManager;

    /**
     * Constructs a new TicketModel.
     * Initializes the ticket manager and populates the observable ticket list.
     *
     * @throws Exception If there's an error retrieving tickets from the data source
     */
    public TicketModel() throws Exception {
        this.ticketManager = new TicketManager();
        ticketObservableList = FXCollections.observableArrayList();
        ticketObservableList.addAll(ticketManager.getAllTickets());
    }

    /**
     * Gets the observable list of tickets for UI binding.
     *
     * @return Observable list containing all tickets
     */
    public ObservableList<Ticket> getTicketObservableList() {
        return ticketObservableList;
    }

    /**
     * Creates a new ticket in the system.
     * Adds the created ticket to the observable collection.
     *
     * @param newTicket The ticket object to be created
     * @throws Exception If the ticket creation fails
     */
    public void createTicket(Ticket newTicket) throws Exception {
        Ticket t = ticketManager.createTicket(newTicket);
        ticketObservableList.add(t);
    }

    /**
     * Deletes a ticket from the system.
     * Removes the ticket from both the data source and observable collection.
     *
     * @param ticketToDelete The ticket to be deleted
     * @throws Exception If the ticket deletion fails
     */
    public void deleteTicket(Ticket ticketToDelete) throws Exception {
        ticketManager.deleteTicket(ticketToDelete);
        ticketObservableList.remove(ticketToDelete);
    }

    /**
     * Refreshes and returns all tickets from the data source.
     * Updates the observable collection with the latest tickets.
     * Note: Currently clears the collection but adds an empty list.
     *
     * @return List of all tickets
     * @throws Exception If tickets retrieval fails
     */
    public List<Ticket> getAllTickets() throws Exception {
        List<Ticket> tickets = new ArrayList<>();

        ticketObservableList.clear();
        ticketObservableList.addAll(tickets);
        return tickets;
    }
}