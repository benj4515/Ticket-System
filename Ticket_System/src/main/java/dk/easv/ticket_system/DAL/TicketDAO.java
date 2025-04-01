package dk.easv.ticket_system.DAL;


import dk.easv.ticket_system.BE.Ticket;
import dk.easv.ticket_system.BE.User;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketDAO implements ITicketDataAccess {
    private DBConnector dbConnector = new DBConnector();

    public TicketDAO() throws IOException {
        this.dbConnector = new DBConnector();
    }

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

    @Override
    public int getTicketID() throws Exception {
        int tID = 0;
        String query = "SELECT ticketID FROM Tickets";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int  ticketId = rs.getInt("ticketID");
                tID = ticketId;
            }
            return tID;
        }
    }

    @Override
    public int getTicketUserID() throws Exception {
        int tUserID = 0;
        String query = "SELECT userID FROM Tickets";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int  ticketUserId = rs.getInt("userID");
                tUserID = ticketUserId;
            }
            return tUserID;
        }
    }

    @Override
    public int getTicketTypeID() throws Exception {
        int tickettypeID = 0;
        String query = "SELECT ticketTypeID FROM Tickets";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int  ticketTipeId = rs.getInt("ticketTypeID");
                tickettypeID = ticketTipeId;
            }
            return tickettypeID;
        }
    }

    @Override
    public Date getPurchaseDate() throws Exception {
        Date buyDate = new Date();
        String query = "SELECT purchaseDate FROM Tickets";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Date  buyThicket = rs.getDate("purchaseDate");
                buyDate = buyThicket;
            }
            return buyDate;
        }
    }

    /*
    @Override
    public void updateTicket (Ticket updatedTicket) throws Exception {

        String updateQuery = "UPDATE dbo.Tickets SET ticketID= ? WHERE eventID = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery))
    }
     */
}
