/**
 * Provides PDF generation functionality for the ticket system.
 * Uses the iText library to create, modify and save PDF documents,
 * primarily for ticket generation and event documentation.
 */
package dk.easv.ticket_system.BLL.Util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class PDFHandler {
    /**
     * Demo method that creates a simple empty PDF file.
     * This demonstrates basic PDF creation functionality using iText.
     * Creates a new PDF document, adds a page, and saves it to the resources directory.
     *
     * @param args Command line arguments (not used)
     * @throws Exception If there's an error during PDF creation or file writing
     */
    public static void main(String args[]) throws Exception {
        // Creating a PdfWriter
        String dest = "Ticket_System/src/main/resources/PDFs/sample.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);

        // Adding a new page
        pdfDoc.addNewPage();

        // Creating a Document
        Document document = new Document(pdfDoc);

        // Closing the document
        document.close();
        System.out.println("PDF Created");
    }
}