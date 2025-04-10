/**
 * Data Access Object (DAO) for Ticket entities in the ticket system.
 * Provides methods to interact with the Tickets table in the database.
 * Handles CRUD operations for tickets including creation, retrieval, and deletion.
 * Implements the ITicketDataAccess interface to provide a standardized way
 * to access ticket data throughout the application.
 */
package dk.easv.ticket_system.DAL;


import dk.easv.ticket_system.BE.Ticket;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BE.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketDAO implements ITicketDataAccess {
    /**
     * Database connector instance for establishing connections to the database.
     */
    private DBConnector dbConnector = new DBConnector();

    /**
     * Constructs a new TicketDAO with a fresh database connector.
     *
     * @throws IOException If there's an error initializing the DB connector
     */
    public TicketDAO() throws IOException {
        this.dbConnector = new DBConnector();
    }

    /**
     * Creates a new ticket in the database.
     * Inserts ticket details including user ID, ticket type, and purchase date.
     * Returns the created ticket with its generated ID.
     *
     * @param newTicket The ticket object containing details to be inserted
     * @return The created ticket object with its generated ID
     * @throws Exception If database operations fail or constraints are violated
     */
    @Override
    public Ticket createTicket(Ticket newTicket) throws Exception {

        String tQuery = "INSERT INTO Tickets (userID, ticketTypeID, purchaseDate) VALUES (?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ticketStmt = conn.prepareStatement(tQuery, Statement.RETURN_GENERATED_KEYS)) {

            ticketStmt.setInt(1, newTicket.getUserID());
            ticketStmt.setInt(2, newTicket.getTicketTypeID());
            ticketStmt.setObject(3, newTicket.getPurchaseDate());

            int affectedRows = ticketStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Ticket creation failed, no rows affected.");
            }
            int generatedTicketID;
            try (ResultSet generatedKeys = ticketStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedTicketID = generatedKeys.getInt(1);
                } else {
                    throw new Exception("Ticket creation failed, no ticketID returned.");
                }
            }
            return new Ticket(generatedTicketID, newTicket.getUserID(), newTicket.getTicketTypeID(), newTicket.getPurchaseDate());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new ticket.");
        }
    }

    /**
     * Retrieves all tickets from the database.
     * Note: The SQL query currently selects from TrueUsers instead of Tickets table,
     * which may cause incorrect results.
     *
     * @return List of all Ticket objects in the database
     * @throws Exception If database operations fail or query is incorrect
     */
    @Override
    public List<Ticket> getAllTickets () throws Exception {
        ArrayList<Ticket> tickets = new ArrayList<>();

        String sql = "SELECT userID, email, passwordHash, roleID FROM TrueUsers ";

        try (Connection conn = dbConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int ticketID = rs.getInt("ticketID");
                int userID = rs.getInt("userID");
                int ticketTypeID = rs.getInt("ticketTypeID");
                LocalDateTime purchaseDate = rs.getObject("purchaseDate", LocalDateTime.class);

                Ticket ticket = new Ticket(ticketID, userID, ticketTypeID, purchaseDate);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot retrieve tickets.");
        }
    }

    /**
     * Deletes a ticket from the database.
     * Permanently removes the ticket record based on its ID.
     *
     * @param ticketToDelete The ticket object to be deleted
     * @throws Exception If the deletion operation fails
     */
    @Override
    public void deleteTicket(Ticket ticketToDelete) throws Exception {

        //create a string with the sql statement to delete a given user from the database
        String sql = "DELETE FROM dbo.Tickets WHERE ticketID = ?;";

        //try with resources to connect to the database and execute the delete statement
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, ticketToDelete.getTicketID());

            stmt.executeUpdate();

            //throw an exception if it fails
        } catch (SQLException e) {
            throw new Exception("Couldn't delete Ticket from database", e);
        }
    }

    /**
     * Gets a ticket ID from the database.
     * Note: Currently returns only the last ID found since it doesn't filter results.
     *
     * @return The ticket ID, or 0 if none found
     * @throws Exception If the database query fails
     */
    @Override
    public int getTicketID() throws Exception {
        int tID = 0;
        String query = "SELECT ticketID FROM Tickets";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int ticketId = rs.getInt("ticketID");
                tID = ticketId;
            }
            return tID;
        }
    }

    /**
     * Gets the user ID associated with a ticket.
     * Note: Currently returns only the last ID found since it doesn't filter results.
     *
     * @return The user ID, or 0 if none found
     * @throws Exception If the database query fails
     */
    @Override
    public int getTicketUserID() throws Exception {
        int tUserID = 0;
        String query = "SELECT userID FROM Tickets";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int ticketUserId = rs.getInt("userID");
                tUserID = ticketUserId;
            }
            return tUserID;
        }
    }

    /**
     * Gets the ticket type ID associated with a ticket.
     * Note: Currently returns only the last ID found since it doesn't filter results.
     *
     * @return The ticket type ID, or 0 if none found
     * @throws Exception If the database query fails
     */
    @Override
    public int getTicketTypeID() throws Exception {
        int tickettypeID = 0;
        String query = "SELECT ticketTypeID FROM Tickets";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int ticketTipeId = rs.getInt("ticketTypeID");
                tickettypeID = ticketTipeId;
            }
            return tickettypeID;
        }
    }

    /**
     * Gets the purchase date of a ticket.
     * Note: Currently returns only the last date found since it doesn't filter results.
     *
     * @return The purchase date, or current date if none found
     * @throws Exception If the database query fails
     */
    @Override
    public Date getPurchaseDate() throws Exception {
        Date buyDate = new Date();
        String query = "SELECT purchaseDate FROM Tickets";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Date buyThicket = rs.getDate("purchaseDate");
                buyDate = buyThicket;
            }
            return buyDate;
        }
    }

    /**
     * Gets the ticket type information for a ticket.
     * Note: The SQL query currently lacks a WHERE clause to filter results.
     *
     * @return The ticket type object with details, or null if not found
     * @throws Exception If the database query fails
     */
    @Override
    public TicketType getTicketType() throws Exception {
        TicketType ticketType = null;
        String sql = "SELECT * FROM TicketType WHERE ticketTypeID";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int tickeTypeID = rs.getInt("ticketTypeID");
                int eventID = rs.getInt("eventID");
                float ticketPrice = rs.getFloat("ticketPrice");
                String ticketDescription = rs.getString("ticketDescription");
                int soldTickets = rs.getInt("soldTickets");
                String ticketColor = rs.getString("ticketColor");

                ticketType = new TicketType(tickeTypeID, eventID, ticketPrice, ticketDescription, soldTickets, ticketColor);
            }
        }
        return ticketType;
    }

}