package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.DAL.EventDAO;
import dk.easv.ticket_system.DAL.IEventsDataAccess;

import java.io.IOException;
import java.util.List;

public class EventManager {
    private final IEventsDataAccess dataAccess;

    public EventManager() throws IOException {
        dataAccess = new EventDAO();
    }

    public Event createEvent(Event newEvent, List<TicketType> newTicketTypes) throws Exception {
        return dataAccess.createEvent(newEvent, newTicketTypes);
    }

    public void deleteEvent(Event eventToDelete) throws Exception {
        dataAccess.deleteEvent(eventToDelete);
    }

    public List<Event> getAllEvents() throws Exception {
        return dataAccess.getAllEvents();
    }

    public void updateEvent(Event updatedEvent) throws Exception {
        dataAccess.updateEvent(updatedEvent);
    }

    public Event eventForEventManager() throws Exception {
        return dataAccess.eventForEventManager();
    }
}
