/**
 * Manages ticket-related business logic in the ticket system.
 * Provides methods for creating, retrieving, and deleting tickets
 * through an abstraction layer between controllers and data access objects.
 */
package dk.easv.ticket_system.BLL.Util;



import dk.easv.ticket_system.BE.Ticket;
import dk.easv.ticket_system.DAL.ITicketDataAccess;
import dk.easv.ticket_system.DAL.TicketDAO;
import org.apache.commons.codec.binary.Base64;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class TicketManager {
    private final ITicketDataAccess dataAccess;    // Data access object for ticket operations


    /**
     * Constructs a TicketManager with a new TicketDAO instance.
     *
     * @throws IOException If there's an error initializing the data access layer
     * @throws GeneralSecurityException If there's a security-related error during initialization
     */
    public TicketManager() throws IOException, GeneralSecurityException {
        dataAccess = new TicketDAO();

    }

    /**
     * Creates a new ticket in the system.
     *
     * @param newTicket The ticket object to create
     * @return The created ticket with assigned ID
     * @throws Exception If there's an error during ticket creation
     */
    public Ticket createTicket(Ticket newTicket) throws Exception {
        return dataAccess.createTicket(newTicket);
    }

    /**
     * Retrieves all tickets in the system.
     *
     * @return A list of all tickets
     * @throws Exception If there's an error retrieving tickets
     */
    public List<Ticket> getAllTickets () throws Exception {
        return dataAccess.getAllTickets();
    }

    /**
     * Deletes an existing ticket.
     *
     * @param ticketToDelete The ticket to be deleted
     * @throws Exception If there's an error during ticket deletion
     */
    public void deleteTicket(Ticket ticketToDelete) throws Exception {
        dataAccess.deleteTicket(ticketToDelete);
    }

}