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
    private final ITicketDataAccess dataAccess;


    public TicketManager() throws IOException, GeneralSecurityException {
        dataAccess = new TicketDAO();

    }

    public Ticket createTicket(Ticket newTicket) throws Exception {
        return dataAccess.createTicket(newTicket);
    }

    public List<Ticket> getAllTickets () throws Exception {
        return dataAccess.getAllTickets();
    }

    public void deleteTicket(Ticket ticketToDelete) throws Exception {
        dataAccess.deleteTicket(ticketToDelete);
    }

}
