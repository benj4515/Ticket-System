/**
 * Data Access Object (DAO) for TicketType entities in the ticket system.
 * Provides methods to interact with the TicketTypes table in the database.
 * Handles CRUD operations for ticket types including creation, retrieval, and deletion.
 * Implements the ITicketTypeDataAccess interface to provide standardized access
 * to ticket type data throughout the application.
 */
package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;



import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAO implements ITicketTypeDataAccess {
    /**
     * Reference to the EventDAO for any event-related operations.
     */
    private EventDAO eventDao;

    /**
     * Database connector instance for establishing connections to the database.
     */
    private DBConnector dbConnector = new DBConnector();




    /**
     * Constructs a new TicketTypeDAO with fresh database connector and event DAO.
     *
     * @throws IOException If there's an error initializing the DB connector
     */
    public TicketTypeDAO() throws IOException {
        this.dbConnector = new DBConnector();
        this.eventDao = new EventDAO();

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
     * Inserts ticket type details including event ID, price, and description.
     * Returns the created ticket type.
     *
     * @param newTicketType The ticket type object containing details to be inserted
     * @return The created ticket type object
     * @throws Exception If database operations fail or constraints are violated
     */
    @Override
    public TicketType createTicketType(TicketType newTicketType) throws Exception {
        String ttQuery = "INSERT INTO TicketTypes (eventID, ticketPrice, ticketDescription) VALUES (?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ttStmt = conn.prepareStatement(ttQuery, Statement.RETURN_GENERATED_KEYS)) {

            ttStmt.setInt(1, newTicketType.getEventID());
            ttStmt.setDouble(2, newTicketType.getTicketPrice());
            ttStmt.setString(3, newTicketType.getTicketDescription());
            ttStmt.executeUpdate();

            return newTicketType;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new TicketType", e);
        }
    }



    /**
     * Deletes a ticket type from the database.
     * Permanently removes the ticket type record based on its ID.
     * Note: This method currently uses eventID instead of ticketTypeID in the WHERE clause.
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
            throw new Exception("Couldn't delete TicketType  from database", e);
        }


    }

    /**
     * Retrieves all ticket types from the database.
     * Gets complete ticket type information including price, description,
     * sales data, and color coding.
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
                String  tDescription = rs.getString("ticketDescription");
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
                int  tSold = rs.getInt("ticketsSold");
                ttSold = tSold;
            }
            return ttSold;
        }
    }
}