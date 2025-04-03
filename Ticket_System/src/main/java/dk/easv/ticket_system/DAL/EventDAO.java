package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BE.User;


import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEventsDataAccess {
    private DBConnector dbConnector = new DBConnector();

    public EventDAO() throws IOException {
        this.dbConnector = new DBConnector();
    }

    /*
    @Override
    public Event createEvent(Event newEvent) throws Exception {
        String eventQuery = "INSERT INTO Events (eventName, eventDate, location, eventDescription, eventStart, eventEnd, eventDateEnd ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement eventStmt = conn.prepareStatement(eventQuery, Statement.RETURN_GENERATED_KEYS)) {

            eventStmt.setString(1, newEvent.geteventTitle());
            eventStmt.setDate(2, newEvent.geteventStartDate());
            eventStmt.setString(3, newEvent.getLocation());
            eventStmt.setString(4, newEvent.geteventDescription());
            eventStmt.setTime(5, java.sql.Time.valueOf(LocalTime.parse(newEvent.geteventStartTime())));
            eventStmt.setTime(6, java.sql.Time.valueOf(LocalTime.parse(newEvent.geteventEndTime())));
            eventStmt.setDate(7, newEvent.getEventEndDate());
            eventStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new Event", e);
        }
        return null;
    }
    */

    @Override
    public Event createEvent(Event newEvent, List<TicketType> newTicketTypes) throws Exception {
        String eventQuery = "INSERT INTO Events (eventName, eventDate, location, eventDescription, eventStart, eventEnd, eventDateEnd) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String tTypeQuery = "INSERT INTO TicketTypes (eventID, ticketPrice, ticketDescription) VALUES (?, ?, ?)";

        try (Connection conn = dbConnector.getConnection()) {
            conn.setAutoCommit(false); // Start transaction


            int generatedEventID;
            try (PreparedStatement eventStmt = conn.prepareStatement(eventQuery, Statement.RETURN_GENERATED_KEYS)) {
                eventStmt.setString(1, newEvent.geteventTitle());
                eventStmt.setDate(2, newEvent.getEventStartDate());
                eventStmt.setString(3, newEvent.getLocation());
                eventStmt.setString(4, newEvent.geteventDescription());
                eventStmt.setTime(5, Time.valueOf(LocalTime.parse(newEvent.geteventStartTime())));
                eventStmt.setTime(6, Time.valueOf(LocalTime.parse(newEvent.geteventEndTime())));
                eventStmt.setDate(7, newEvent.getEventEndDate());

                int affectedRows = eventStmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new Exception("Event creation failed, no rows affected.");
                }

                try (ResultSet generatedKeys = eventStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedEventID = generatedKeys.getInt(1);
                    } else {
                        throw new Exception("Event creation failed, no eventID returned.");
                    }
                }
            }


            try (PreparedStatement tTypeStmt = conn.prepareStatement(tTypeQuery)) {
                for (TicketType ticketType : newTicketTypes) {
                    tTypeStmt.setInt(1, generatedEventID);
                    tTypeStmt.setFloat(2, ticketType.getTicketPrice());
                    tTypeStmt.setString(3, ticketType.getTicketDescription());
                    tTypeStmt.addBatch();

                }
                tTypeStmt.executeBatch();
            }

            conn.commit();
            return new Event(generatedEventID, newEvent.geteventTitle(), newEvent.getEventStartDate(), newEvent.getLocation(),
                    newEvent.geteventDescription(), newEvent.geteventStartTime(), newEvent.geteventEndTime(), newEvent.getEventEndDate());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new event.", e);
        }
    }


    @Override
    public void deleteEvent(Event eventToDelete) throws Exception {

        //create a string with the sql statement to delete a given Event from the database
        String sql = "DELETE FROM dbo.Events WHERE eventID = ?;";

        //try with resources to connect to the database and execute the delete statement
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, eventToDelete.getEventID());

            stmt.executeUpdate();

            //throw an exception if it fails
        } catch (SQLException e) {
            throw new Exception("Couldn't delete Event from database", e);
        }
    }



    @Override
    public List<Event> getAllEvents() throws Exception {
        ArrayList<Event> events = new ArrayList<>();

        String sql = "SELECT eventID, eventName, eventDate, location, eventDescription, eventStart, eventEnd, eventDateEnd  FROM Events";

        try (Connection conn = dbConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {


                int eventID = rs.getInt("eventID");
                String eventTitle = rs.getString("eventName");
                Date eventStartDate = rs.getDate("eventDate");
                String location = rs.getString("location");
                String eventDescription = rs.getString("eventDescription");
                String eventStartTime = String.valueOf(rs.getTime("eventStart").toLocalTime());
                String eventEndTime = String.valueOf(rs.getTime("eventEnd").toLocalTime());
                Date eventEndDate = rs.getDate("eventDateEnd");
                //String recTransport = rs.getString("recommendedTransport");

                Event event = new Event(eventID, eventTitle, eventStartDate, location,eventDescription, eventStartTime, eventEndTime, eventEndDate);
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot retrieve events.");
        }
    }



    @Override
    public Event eventForEventManager() throws Exception {

        String sql = "SELECT eventName, eventDate, location, eventStart, eventEnd, recommendedTransport FROM Events";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            String eventTitle = rs.getString("eventName");
            Date eventStartDate = rs.getDate("eventDate");
            String location = rs.getString("location");
            String eventStartTime = String.valueOf(rs.getTime("eventStart").toLocalTime());
            String eventEnd = String.valueOf(rs.getTime("eventEnd").toLocalTime());
            String recTransport = rs.getString("recommendedTransport");

            Event event = new Event(eventTitle, eventStartDate, location, eventStartTime, eventEnd, recTransport);
            event.seteventTitle(eventTitle);
            event.seteventStartDate(eventStartDate);
            event.setLocation(location);
            event.seteventStartTime(eventStartTime);
            event.seteventEndTime(eventEnd);
            event.setRecTransport(recTransport);

            return event;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot retrieve event info for management thumbnail.");
        }
    }



    public void updateEvent(Event updatedEvent) throws Exception {

        String updateQuery = "UPDATE dbo.Events SET eventName = ?, eventDate = ?, location = ?, eventStart = ?, eventEnd = ?, eventDescription = ?, recommendedTransport = ? WHERE eventID = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, updatedEvent.geteventTitle());
            stmt.setDate(2, updatedEvent.geteventStartDate());
            stmt.setString(3, updatedEvent.getLocation());
            stmt.setTime(4, java.sql.Time.valueOf(updatedEvent.geteventStartTime()));
            stmt.setTime(5, java.sql.Time.valueOf(updatedEvent.geteventEndTime()));
            stmt.setString(6, updatedEvent.geteventDescription());
            stmt.setString(7, updatedEvent.getRecTransport());
            stmt.setInt(8, updatedEvent.getEventID());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new Exception("No event was updated. Check if the eventID exists.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't update Event");
        }
    }

}
