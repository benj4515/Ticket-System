package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.Util.EventManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventModel {
    private final ObservableList<Event> observableEvents;
    private final EventManager eventManager;

    public EventModel() throws Exception {
        this.eventManager = new EventManager();
        observableEvents = FXCollections.observableArrayList();
        observableEvents.addAll(eventManager.getAllEvents());
    }

    public ObservableList<Event> getObservableEvents() {
        return observableEvents;
    }

    public void createEvent(Event newEvent, List<TicketType> ticketTypes) throws Exception {
        Event e = eventManager.createEvent(newEvent, ticketTypes);
        observableEvents.add(e);
    }

    public void deleteEvent(Event eventToDelete) throws Exception {
        eventManager.deleteEvent(eventToDelete);
        observableEvents.remove(eventToDelete);
    }

    public List<Event> getAllEvents() throws Exception {
       List<Event> events = eventManager.getAllEvents();

       observableEvents.clear();
       observableEvents.addAll(events);
       return events;
    }

    public void updateEvent(Event updatedEvent) throws Exception {
        eventManager.updateEvent(updatedEvent);
    }

    public Event getEventForEventManagement() throws Exception {
        return eventManager.eventForEventManager();
    }

    public void assignCoordinatorToEvent(User user, Event event) throws Exception {
        eventManager.assignCoordinatorToEvent(user, event);
    }

    public List<User> getCoordinatorsForEvent(int eventID) {
        return eventManager.getCoordinatorsForEvent(eventID);
    }

    public void removeCoordinatorFromEvent(User coordinator, Event event) throws Exception {
        eventManager.removeCoordinatorFromEvent(coordinator, event);
    }
}
