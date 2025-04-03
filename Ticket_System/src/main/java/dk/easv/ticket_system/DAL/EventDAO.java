package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEventsDataAccess, ITicketTypeDataAccess {
    private DBConnector dbConnector = new DBConnector();

    public EventDAO() throws IOException {
        this.dbConnector = new DBConnector();
    }

    int CreatedEventID;


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

            try (ResultSet generatedKeys = eventStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    CreatedEventID = generatedKeys.getInt(1);
                } else {
                    throw new Exception("User creation failed, no userID returned.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new Event", e);
        }
        return null;
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

    @Override
    public int getEventIDByName(String eventName) throws Exception {

        String sql = "SELECT eventID FROM Event WHERE eventName = ?";

        try (Connection conn = dbConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("eventID");
            }
            return -1;
        }
    }

    @Override
    //ticket name, description, price - probably dont work since you have to manually KNOW and ENTER eventID
    public TicketType createTicketType(TicketType newTicketType) throws Exception {
        String ttQuery = "INSERT INTO TicketTypes (eventID, ticketPrice, ticketDescription) VALUES (?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ttStmt = conn.prepareStatement(ttQuery, Statement.RETURN_GENERATED_KEYS)) {

            ttStmt.setInt(1, CreatedEventID);
            ttStmt.setFloat(2, newTicketType.getTicketPrice());
            ttStmt.setString(3, newTicketType.getTicketDescription());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new TicketType", e);
        }
        return null;
    }

    @Override
    public void deleteTicketType(TicketType ticketTypeToBeDeleted) throws Exception {
        //create a string with the sql statement to delete a given TicketType from the database
        String sql = "DELETE FROM TicketTypes WHERE ticketTypeID = ?;";

        //try with resources to connect to the database and execute the delete statement
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, ticketTypeToBeDeleted.getEventID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Couldn't delete TicketType  from database", e);
        }


    }

    @Override
    public List<TicketType> getAllTicketTypes() throws Exception {
        ArrayList<TicketType> tTypes = new ArrayList<>();

        String sql = "SELECT ticketTypeID, eventID, ticketPrice," +
                "ticketDescription, soldTickets FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int ticketTypeID = rs.getInt("ticketTypeID");
                int eventID = rs.getInt("eventID");
                float ticketPrice = rs.getFloat("ticketPrice");
                String ticketDescription = rs.getString("ticketDescription");
                int ticketsSold = rs.getInt("soldTickets");

                TicketType typeTicket = new TicketType(ticketTypeID, eventID, ticketPrice, ticketDescription, ticketsSold);
                tTypes.add(typeTicket);
            }
            return tTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot retrieve TicketTypes.");
        }
    }

    @Override
    public int getTicketTypeID() throws Exception {
        int ttid = 0;
        String query = "SELECT ticketTypeID FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int ticketTypeID = rs.getInt("ticketTypeID");
                ttid = ticketTypeID;
            }
            return ttid;
        }
    }

    @Override
    public int getTicketTypeEventID() throws Exception {
        int tteid = 0;
        String query = "SELECT eventID FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int ticketTypeEventID = rs.getInt("eventID");
                tteid = ticketTypeEventID;
            }
            return tteid;
        }
    }

    @Override
    public BigDecimal getTicketPrice() throws Exception {
        BigDecimal tPrice = BigDecimal.ZERO;
        String query = "SELECT ticketPrice FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BigDecimal ticketPrice = rs.getBigDecimal("ticketPrice");
                tPrice = ticketPrice;
            }
            return tPrice;
        }
    }

    @Override
    public String getTicketTypeDescription() throws Exception {
        String ttDescription = "";
        String query = "SELECT ticketDescription FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String  tDescription = rs.getString("ticketDescription");
                ttDescription = tDescription;
            }
            return ttDescription;
        }
    }

    @Override
    public int getSoldTickets() throws Exception {
        int ttSold = 0;
        String query = "SELECT ticketsSold FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int  tSold = rs.getInt("ticketsSold");
                ttSold = tSold;
            }
            return ttSold;
        }
    }

}
