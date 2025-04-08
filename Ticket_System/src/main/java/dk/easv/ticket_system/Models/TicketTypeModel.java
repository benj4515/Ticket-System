/**
 * Model class for managing TicketTypes in the ticket system.
 * Acts as an intermediary between the UI components and the business logic layer.
 * Maintains an observable collection of ticket types for UI binding and delegates
 * operations to the TicketTypeManager business logic class.
 */
package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BLL.Util.TicketTypeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class TicketTypeModel {

    /**
     * Observable collection of TicketType objects for UI binding.
     * Allows JavaFX UI components to automatically update when the collection changes.
     */
    private final ObservableList<TicketType> ObservableTicketTypes;

    /**
     * Business logic manager for ticket type operations.
     * Handles data processing and persistence operations for ticket types.
     */
    private final TicketTypeManager ticketTypeManager;

    /**
     * Constructs a new TicketTypeModel.
     * Initializes the ticket type manager and populates the observable ticket type list.
     *
     * @throws Exception If there's an error retrieving ticket types from the data source
     */
    public TicketTypeModel() throws Exception {
        this.ticketTypeManager = new TicketTypeManager();
        ObservableTicketTypes = FXCollections.observableArrayList();
        ObservableTicketTypes.addAll(ticketTypeManager.getAllTicketTypes());
    }

    /**
     * Finds and returns a specific ticket type by its ID.
     * Searches the observable collection for a matching ticket type.
     *
     * @param ticketID The ID of the ticket type to find
     * @return The matching TicketType object, or null if not found
     */
    public TicketType getTicketTypeByID(int ticketID) {
        for (TicketType ticketType : ObservableTicketTypes) {
            if (ticketType.getTicketTypeID() == ticketID) {
                return ticketType;
            }
        }
        return null; // Return null if no matching TicketType is found
    }

    /**
     * Gets the observable list of ticket types for UI binding.
     *
     * @return Observable list containing all ticket types
     */
    public ObservableList<TicketType> getObservableTicketTypes() {
        return ObservableTicketTypes;
    }

    /**
     * Creates a new ticket type in the system.
     * Adds the created ticket type to the observable collection.
     *
     * @param newTicketType The ticket type object to be created
     * @throws Exception If the ticket type creation fails
     */
    public void createTicketType(TicketType newTicketType) throws Exception {
        TicketType t = ticketTypeManager.createTicketType(newTicketType);
        ObservableTicketTypes.add(t);
    }

    /**
     * Deletes a ticket type from the system.
     * Removes the ticket type from both the data source and observable collection.
     *
     * @param ticketTypeToBeDeleted The ticket type to be deleted
     * @throws Exception If the ticket type deletion fails
     */
    public void deleteTicketType(TicketType ticketTypeToBeDeleted) throws Exception {
        ticketTypeManager.deleteTicketType(ticketTypeToBeDeleted);
        ObservableTicketTypes.remove(ticketTypeToBeDeleted);
    }
}