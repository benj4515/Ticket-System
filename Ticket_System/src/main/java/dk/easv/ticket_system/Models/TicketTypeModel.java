package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BLL.Util.TicketTypeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class TicketTypeModel {

    private final ObservableList<TicketType> ObservableTicketTypes;
    private final TicketTypeManager ticketTypeManager;



    public TicketTypeModel() throws Exception {
        this.ticketTypeManager = new TicketTypeManager();
        ObservableTicketTypes = FXCollections.observableArrayList();
        ObservableTicketTypes.addAll(ticketTypeManager.getAllTicketTypes());

    }
    // Method to get a TicketType by its ID
    public TicketType getTicketTypeByID(int ticketID) {
        for (TicketType ticketType : ObservableTicketTypes) {
            if (ticketType.getTicketTypeID() == ticketID) {
                return ticketType;
            }
        }
        return null; // Return null if no matching TicketType is found
    }

    public ObservableList<TicketType> getObservableTicketTypes() {
        return ObservableTicketTypes;
    }

    public void createTicketType(TicketType newTicketType) throws Exception {
        TicketType t = ticketTypeManager.createTicketType(newTicketType);
        ObservableTicketTypes.add(t);
    }

    public void deleteTicketType(TicketType ticketTypeToBeDeleted) throws Exception {
        ticketTypeManager.deleteTicketType(ticketTypeToBeDeleted);
        ObservableTicketTypes.remove(ticketTypeToBeDeleted);
    }





}
