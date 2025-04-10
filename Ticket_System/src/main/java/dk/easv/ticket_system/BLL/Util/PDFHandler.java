/**
 * Provides PDF generation functionality for the ticket system.
 * Uses the iText library to create, modify and save PDF documents,
 * primarily for ticket generation and event documentation.
 */
package dk.easv.ticket_system.BLL.Util;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

import java.io.File;
import java.util.ArrayList;


public class PDFHandler {
    public static void createPDF(String filepath, String QRCodePath, Event event, ObservableList<TicketType> selectedTickets, ArrayList<CheckBox> selectedUniversalTickets) throws Exception {
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(String.valueOf(file));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        pdf.addNewPage();


        document.add(new Paragraph("Ticket Confirmation:").setFontSize(20).setBold());
        document.add(new Paragraph("Ticket Details:").setFontSize(16).setBold());

        for(TicketType ticketType : selectedTickets) {

            document.add(new Paragraph("Ticket Type: " + ticketType.getTicketDescription()));
            document.add(new Paragraph("Price: " + ticketType.getTicketPrice() + " DKK"));
        }

        for(CheckBox checkBox : selectedUniversalTickets) {
            if(checkBox.isSelected()) {
                document.add(new Paragraph("Universal Ticket: " + checkBox.getText()).setFontSize(14).setBold());
            }
        }


        Image qrImage = new Image(ImageDataFactory.create(QRCodePath)).setWidth(150).setHeight(150);
        String eventTitle = event.geteventTitle();
        String eventDescription = event.geteventDescription();
        String eventStartDate = event.geteventStartDate().toString();
        String eventStartTime = event.geteventStartTime();
        String eventEndTime = event.geteventEndTime();
        String eventEndDate = event.getEventEndDate().toString();
        String eventLocation = event.getLocation();
        document.add(qrImage);
        document.add(new Paragraph("✂- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(14).setBold());
        document.add(new Paragraph("Event details:").setFontSize(20).setBold());
        document.add(new Paragraph(eventTitle));
        document.add(new Paragraph(eventDescription));
        document.add(new Paragraph("Start time: " + eventStartDate + " " + eventStartTime));
        document.add(new Paragraph("End time: " + eventEndDate + " " + eventEndTime));
        document.add(new Paragraph(eventLocation));


        // Closing the document
        document.close();
        System.out.println("PDF Created");
    }
}
