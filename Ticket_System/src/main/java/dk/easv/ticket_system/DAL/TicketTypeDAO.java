package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;



import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAO implements ITicketTypeDataAccess {
    private EventDAO eventDao;
    private DBConnector dbConnector = new DBConnector();




    public TicketTypeDAO() throws IOException {
        this.dbConnector = new DBConnector();
        this.eventDao = new EventDAO();

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
