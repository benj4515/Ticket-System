package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BE.User;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public interface IEventsDataAccess {




    Event createEventAndTicketTypes (Event newEvent, List<TicketType> TicketTypes) throws Exception;

    void deleteEvent(Event eventToDelete) throws Exception;

    void updateEvent(Event updatedEvent) throws Exception;

    List<Event> getAllEvents() throws Exception;

    Event eventForEventManager() throws Exception;


    Event assignCoordinatorToEvent(User user, Event event) throws Exception;


    List<User> getCoordinatorsForEvent(int eventID);
}
