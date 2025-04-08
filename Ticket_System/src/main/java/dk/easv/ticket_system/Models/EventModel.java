/**
 * Model class for managing Events in the ticket system.
 * Acts as an intermediary between the UI components and the business logic layer.
 * Maintains an observable collection of events for UI binding and delegates
 * operations to the EventManager business logic class.
 */
package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.Util.EventManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventModel {
    /**
     * Observable collection of Event objects for UI binding.
     * Allows JavaFX UI components to automatically update when the collection changes.
     */
    private final ObservableList<Event> observableEvents;

    /**
     * Business logic manager for event operations.
     * Handles data processing and persistence operations for events.
     */
    private final EventManager eventManager;

    /**
     * Constructs a new EventModel.
     * Initializes the event manager and populates the observable event list.
     *
     * @throws Exception If there's an error retrieving events from the data source
     */
    public EventModel() throws Exception {
        this.eventManager = new EventManager();
        observableEvents = FXCollections.observableArrayList();
        observableEvents.addAll(eventManager.getAllEvents());
    }

    /**
     * Gets the observable list of events for UI binding.
     *
     * @return Observable list containing all events
     */
    public ObservableList<Event> getObservableEvents() {
        return observableEvents;
    }

    /**
     * Creates a new event with associated ticket types.
     * Adds the created event to the observable collection.
     *
     * @param newEvent The event object to be created
     * @param ticketTypes List of ticket types to associate with the event
     * @throws Exception If the event creation fails
     */
    public void createEvent(Event newEvent, List<TicketType> ticketTypes) throws Exception {
        Event e = eventManager.createEvent(newEvent, ticketTypes);
        observableEvents.add(e);
    }

    /**
     * Deletes an event from the system.
     * Removes the event from both the data source and observable collection.
     *
     * @param eventToDelete The event to be deleted
     * @throws Exception If the event deletion fails
     */
    public void deleteEvent(Event eventToDelete) throws Exception {
        eventManager.deleteEvent(eventToDelete);
        observableEvents.remove(eventToDelete);
    }

    /**
     * Refreshes and returns all events from the data source.
     * Updates the observable collection with the latest events.
     *
     * @return List of all events
     * @throws Exception If events retrieval fails
     */
    public List<Event> getAllEvents() throws Exception {
        List<Event> events = eventManager.getAllEvents();

        observableEvents.clear();
        observableEvents.addAll(events);
        return events;
    }

    /**
     * Updates an existing event's details.
     * Applies changes to the event in the data source.
     *
     * @param updatedEvent The event object with updated information
     * @throws Exception If the event update fails
     */
    public void updateEvent(Event updatedEvent) throws Exception {
        eventManager.updateEvent(updatedEvent);
    }

    /**
     * Retrieves event information formatted for the event management interface.
     *
     * @return Event object with information for the event manager view
     * @throws Exception If the retrieval fails
     */
    public Event getEventForEventManagement() throws Exception {
        return eventManager.eventForEventManager();
    }

    /**
     * Assigns a coordinator to an event.
     * Creates a relationship linking the specified user as coordinator with the event.
     *
     * @param user The coordinator user to be assigned
     * @param event The event to assign the coordinator to
     * @throws Exception If the assignment operation fails
     */
    public void assignCoordinatorToEvent(User user, Event event) throws Exception {
        eventManager.assignCoordinatorToEvent(user, event);
    }

    /**
     * Gets all coordinators assigned to a specific event.
     *
     * @param eventID The ID of the event to get coordinators for
     * @return List of User objects representing the assigned coordinators
     */
    public List<User> getCoordinatorsForEvent(int eventID) {
        return eventManager.getCoordinatorsForEvent(eventID);
    }

    /**
     * Removes a coordinator's assignment from an event.
     * Deletes the relationship between the coordinator and event.
     *
     * @param coordinator The coordinator user to be removed
     * @param event The event to remove the coordinator from
     * @throws Exception If the removal operation fails
     */
    public void removeCoordinatorFromEvent(User coordinator, Event event) throws Exception {
        eventManager.removeCoordinatorFromEvent(coordinator, event);
    }
}