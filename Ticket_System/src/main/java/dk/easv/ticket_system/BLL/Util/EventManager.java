/**
 * Manages event-related business logic in the ticket system.
 * Provides methods for creating, updating, deleting and retrieving events,
 * as well as managing the association between events and coordinators.
 */
package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.DAL.EventDAO;
import dk.easv.ticket_system.DAL.IEventsDataAccess;

import java.io.IOException;
import java.util.List;

public class EventManager {
    private final IEventsDataAccess dataAccess;    // Data access object for event operations

    /**
     * Constructs an EventManager with a new EventDAO instance.
     *
     * @throws IOException If there's an error initializing the data access layer
     */
    public EventManager() throws IOException {
        dataAccess = new EventDAO();
    }

    /**
     * Creates a new event with associated ticket types.
     *
     * @param newEvent The event object to create
     * @param newTicketTypes List of ticket types associated with the event
     * @return The created event with assigned ID
     * @throws Exception If there's an error during event creation
     */
    public Event createEvent(Event newEvent, List<TicketType> newTicketTypes) throws Exception {
        return dataAccess.createEventAndTicketTypes(newEvent, newTicketTypes);
    }

    /**
     * Deletes an existing event.
     *
     * @param eventToDelete The event to be deleted
     * @throws Exception If there's an error during event deletion
     */
    public void deleteEvent(Event eventToDelete) throws Exception {
        dataAccess.deleteEvent(eventToDelete);
    }

    /**
     * Retrieves all events in the system.
     *
     * @return A list of all events
     * @throws Exception If there's an error retrieving events
     */
    public List<Event> getAllEvents() throws Exception {
        return dataAccess.getAllEvents();
    }

    /**
     * Updates an existing event with new information.
     *
     * @param updatedEvent The event with updated information
     * @throws Exception If there's an error during event update
     */
    public void updateEvent(Event updatedEvent) throws Exception {
        dataAccess.updateEvent(updatedEvent);
    }

    /**
     * Retrieves an event specifically for the event manager.
     *
     * @return An event object for management purposes
     * @throws Exception If there's an error retrieving the event
     */
    public Event eventForEventManager() throws Exception {
        return dataAccess.eventForEventManager();
    }

    /**
     * Assigns a coordinator user to an event.
     *
     * @param user The coordinator user to assign
     * @param event The event to which the coordinator will be assigned
     * @return The updated event
     * @throws Exception If there's an error during assignment
     */
    public Event assignCoordinatorToEvent(User user, Event event) throws Exception {
        return dataAccess.assignCoordinatorToEvent(user, event);
    }

    /**
     * Gets all coordinators assigned to a specific event.
     *
     * @param eventID The ID of the event
     * @return A list of coordinator users
     */
    public List<User> getCoordinatorsForEvent(int eventID) {
        return dataAccess.getCoordinatorsForEvent(eventID);
    }

    /**
     * Removes a coordinator from an event.
     *
     * @param coordinator The coordinator user to remove
     * @param event The event from which the coordinator will be removed
     * @throws Exception If there's an error during removal
     */
    public void removeCoordinatorFromEvent(User coordinator, Event event) throws Exception {
        dataAccess.removeCoordinatorFromEvent(coordinator, event);
    }
}