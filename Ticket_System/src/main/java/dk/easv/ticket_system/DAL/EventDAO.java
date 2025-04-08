/**
 * Data Access Object (DAO) for Event and TicketType entities in the ticket system.
 * Provides methods to interact with the Events and TicketTypes tables in the database.
 * Handles CRUD operations for events and ticket types, as well as coordinator assignments.
 * Implements both IEventsDataAccess and ITicketTypeDataAccess interfaces to provide
 * comprehensive data access capabilities for the event management subsystem.
 */
package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BE.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEventsDataAccess, ITicketTypeDataAccess {
    /**
     * Database connector instance for establishing connections to the database.
     */
    private DBConnector dbConnector = new DBConnector();

    /**
     * Constructs a new EventDAO with a fresh database connector.
     *
     * @throws IOException If there's an error initializing the DB connector
     */
    public EventDAO() throws IOException {
        this.dbConnector = new DBConnector();
    }

    /**
     * Stores the ID of the most recently created event.
     * Used for associating ticket types with their parent event.
     */
    int CreatedEventID;

    /**
     * Creates a new event with associated ticket types in a single transaction.
     * Inserts the event details into Events table and creates related ticket types 
     * in the TicketTypes table. Uses a transaction to ensure data consistency.
     *
     * @param newEvent The event object containing event details to be inserted
     * @param TicketTypes List of ticket types to be associated with the event
     * @return The created event object with its generated ID
     * @throws Exception If database operations fail or data constraints are violated
     */
    @Override
    public Event createEventAndTicketTypes(Event newEvent, List<TicketType> TicketTypes) throws Exception {
        String eventQuery = "INSERT INTO Events (eventName, eventDate, location, eventDescription, eventStart, eventEnd, eventDateEnd) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String tTypeQuery = "INSERT INTO TicketTypes (eventID, ticketPrice, ticketDescription, ticketName) VALUES (?, ?, ?, ?)";

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
                        newEvent.setGeneratedEventID(generatedEventID);
                    } else {
                        throw new Exception("Event creation failed, no eventID returned.");
                    }
                }
            }

            try (PreparedStatement tTypeStmt = conn.prepareStatement(tTypeQuery)) {
                for (TicketType ticketType : TicketTypes) {
                    tTypeStmt.setInt(1, newEvent.getGeneratedEventID());
                    tTypeStmt.setDouble(2, ticketType.getTicketPrice());
                    tTypeStmt.setString(3, ticketType.getTicketDescription());
                    tTypeStmt.setString(4, ticketType.getTicketName());
                    tTypeStmt.addBatch();
                }
                tTypeStmt.executeBatch();
            }

            conn.commit();
            return newEvent;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new event.", e);
        }
    }

    /**
     * Deletes an event from the database.
     * Permanently removes the event record based on its ID.
     * Note: This may trigger cascading deletes for related ticket types 
     * depending on database constraints.
     *
     * @param eventToDelete The event object to be deleted
     * @throws Exception If the deletion operation fails
     */
    @Override
    public void deleteEvent(Event eventToDelete) throws Exception {
        //create a string with the sql statement to delete a given Event from the database
        String sql = "DELETE FROM dbo.Events WHERE eventID = ?;";

        //try with resources to connect to the database and execute the delete statement
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventToDelete.getEventID());

            stmt.executeUpdate();

            //throw an exception if it fails
        } catch (SQLException e) {
            throw new Exception("Couldn't delete Event from database", e);
        }
    }

    /**
     * Retrieves all events from the database.
     * Fetches complete event information including dates, times, location and description.
     * Does not load associated ticket types or coordinators.
     *
     * @return List of all Event objects in the database
     * @throws Exception If the database query fails
     */
    @Override
    public List<Event> getAllEvents() throws Exception {
        ArrayList<Event> events = new ArrayList<>();

        String sql = "SELECT eventID, eventName, eventDate, location, eventDescription, eventStart, eventEnd, eventDateEnd FROM Events";

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

                Event event = new Event(eventID, eventTitle, eventStartDate, location, eventDescription, eventStartTime, eventEndTime, eventEndDate);
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot retrieve events.");
        }
    }

    /**
     * Retrieves event information specifically for the event manager interface.
     * Gets a subset of event details focused on display needs for event management.
     * Note: Currently retrieves only one event without specifying which one.
     *
     * @return An Event object with basic information
     * @throws Exception If the database query fails
     */
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot retrieve event info for management thumbnail.");
        }
    }

    /**
     * Assigns a coordinator to an event.
     * Creates a relationship in the AssignedEvents junction table linking
     * the specified user (coordinator) with the given event.
     *
     * @param user The coordinator user to be assigned
     * @param event The event to assign the coordinator to
     * @return The event object that was updated
     * @throws Exception If the assignment operation fails
     */
    @Override
    public Event assignCoordinatorToEvent(User user, Event event) throws Exception {
        String sql = "INSERT INTO AssignedEvents (userID, eventID) VALUES (?, ?)";

        try (Connection conn = dbConnector.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, user.getUserID());
                stmt.setInt(2, event.getEventID());

                stmt.executeUpdate();
                conn.commit(); // Commit transaction
            } catch (SQLException e) {
                conn.rollback(); // Rollback transaction on error
                throw new Exception("Couldn't assign coordinator to event.", e);
            }
        }
        return event;
    }

    /**
     * Gets all coordinators assigned to a specific event.
     * Retrieves user information from TrueUsers and UserDetails tables
     * for coordinators linked to the specified event in AssignedEvents.
     *
     * @param eventID The ID of the event to get coordinators for
     * @return List of User objects representing the assigned coordinators
     */
    @Override
    public List<User> getCoordinatorsForEvent(int eventID) {
        List<User> coordinators = new ArrayList<>();
        String sql = "SELECT u.userID, ud.firstName, ud.lastName FROM TrueUsers u " +
                "JOIN AssignedEvents ae ON u.userID = ae.userID " +
                "JOIN UserDetails ud ON u.userID = ud.userID " +
                "WHERE ae.eventID = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                User coordinator = new User(userID, firstName, lastName);
                coordinators.add(coordinator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordinators;
    }

    /**
     * Removes a coordinator's assignment from an event.
     * Deletes the relationship between the coordinator and event
     * from the AssignedEvents junction table.
     *
     * @param coordinator The coordinator user to be removed
     * @param event The event to remove the coordinator from
     */
    @Override
    public void removeCoordinatorFromEvent(User coordinator, Event event) {
        String sql = "DELETE FROM AssignedEvents WHERE userID = ? AND eventID = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, coordinator.getUserID());
            stmt.setInt(2, event.getEventID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                // Optional: Log that no records were deleted
                System.out.println("No coordinator-event relationship found to delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider throwing this exception to be handled at a higher level
        }
    }

    /**
     * Updates an existing event's details in the database.
     * Modifies all fields of the event record based on the provided Event object.
     *
     * @param updatedEvent The event object containing updated information
     * @throws Exception If the update operation fails
     */
    public void updateEvent(Event updatedEvent) throws Exception {
        String updateQuery = "UPDATE dbo.Events SET eventName = ?, eventDate = ?, location = ?, eventDescription = ?, eventStart = ?, eventEnd = ?, eventDateEnd = ? WHERE eventID = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, updatedEvent.geteventTitle());
            stmt.setDate(2, updatedEvent.geteventStartDate());
            stmt.setString(3, updatedEvent.getLocation());
            stmt.setTime(5, Time.valueOf(LocalTime.parse(updatedEvent.geteventStartTime())));
            stmt.setTime(6, Time.valueOf(LocalTime.parse(updatedEvent.geteventEndTime())));
            stmt.setString(4, updatedEvent.geteventDescription());
            stmt.setDate(7, updatedEvent.getEventEndDate());
            stmt.setInt(8, updatedEvent.getEventID());

            stmt.executeUpdate();

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new Exception("No event was updated. Check if the eventID exists.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't update Event");
        }
    }

    /**
     * Retrieves an event ID by its name.
     * Searches for an event with the specified name and returns its ID.
     *
     * @param eventName The name of the event to find
     * @return The event ID if found, -1 otherwise
     * @throws Exception If the database query fails
     */
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

    /**
     * Creates a new ticket type in the database.
     * Uses the CreatedEventID field to associate with an event.
     * Note: This method is incomplete as it doesn't execute the prepared statement.
     *
     * @param newTicketType The ticket type object containing details to insert
     * @return Should return the created ticket type but currently returns null
     * @throws Exception If the creation operation fails
     */
    @Override
    public TicketType createTicketType(TicketType newTicketType) throws Exception {
        String ttQuery = "INSERT INTO TicketTypes (eventID, ticketPrice, ticketDescription) VALUES (?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ttStmt = conn.prepareStatement(ttQuery, Statement.RETURN_GENERATED_KEYS)) {

            ttStmt.setInt(1, CreatedEventID);
            ttStmt.setDouble(2, newTicketType.getTicketPrice());
            ttStmt.setString(3, newTicketType.getTicketDescription());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new TicketType", e);
        }
        return null;
    }

    /**
     * Deletes a ticket type from the database.
     * Permanently removes the ticket type record based on its ID.
     *
     * @param ticketTypeToBeDeleted The ticket type to be deleted
     * @throws Exception If the deletion operation fails
     */
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
            throw new Exception("Couldn't delete TicketType from database", e);
        }
    }

    /**
     * Retrieves all ticket types from the database.
     * Gets complete ticket type information including price, description, and sales data.
     *
     * @return List of all TicketType objects in the database
     * @throws Exception If the database query fails
     */
    @Override
    public List<TicketType> getAllTicketTypes() throws Exception {
        ArrayList<TicketType> tTypes = new ArrayList<>();

        String sql = "SELECT ticketTypeID, eventID, ticketPrice," +
                "ticketDescription, soldTickets, ticketColor FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int ticketTypeID = rs.getInt("ticketTypeID");
                int eventID = rs.getInt("eventID");
                float ticketPrice = rs.getFloat("ticketPrice");
                String ticketDescription = rs.getString("ticketDescription");
                int ticketsSold = rs.getInt("soldTickets");
                String ticketColor = rs.getString("ticketColor");

                TicketType typeTicket = new TicketType(ticketTypeID, eventID, ticketPrice, ticketDescription, ticketsSold, ticketColor);
                tTypes.add(typeTicket);
            }
            return tTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot retrieve TicketTypes.");
        }
    }

    /**
     * Gets a ticket type ID from the database.
     * Note: Currently returns only the last ID found since it doesn't filter results.
     *
     * @return The ticket type ID, or 0 if none found
     * @throws Exception If the database query fails
     */
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

    /**
     * Gets the event ID associated with a ticket type.
     * Note: Currently returns only the last ID found since it doesn't filter results.
     *
     * @return The event ID, or 0 if none found
     * @throws Exception If the database query fails
     */
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

    /**
     * Gets a ticket price from the database.
     * Note: Currently returns only the last price found since it doesn't filter results.
     *
     * @return The ticket price as a BigDecimal, or ZERO if none found
     * @throws Exception If the database query fails
     */
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

    /**
     * Gets a ticket type description from the database.
     * Note: Currently returns only the last description found since it doesn't filter results.
     *
     * @return The ticket description, or an empty string if none found
     * @throws Exception If the database query fails
     */
    @Override
    public String getTicketTypeDescription() throws Exception {
        String ttDescription = "";
        String query = "SELECT ticketDescription FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String tDescription = rs.getString("ticketDescription");
                ttDescription = tDescription;
            }
            return ttDescription;
        }
    }

    /**
     * Gets the number of tickets sold for a ticket type.
     * Note: Currently returns only the last count found since it doesn't filter results.
     *
     * @return The number of tickets sold, or 0 if none found
     * @throws Exception If the database query fails
     */
    @Override
    public int getSoldTickets() throws Exception {
        int ttSold = 0;
        String query = "SELECT ticketsSold FROM TicketTypes";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int tSold = rs.getInt("ticketsSold");
                ttSold = tSold;
            }
            return ttSold;
        }
    }
}