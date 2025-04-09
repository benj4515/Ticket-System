/**
 * Provides PDF generation functionality for the ticket system.
 * Uses the iText library to create, modify and save PDF documents,
 * primarily for ticket generation and event documentation.
 */
package dk.easv.ticket_system.BLL.Util;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import javafx.scene.control.Cell;

import java.io.File;


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
    public static void createPDF(String filepath, String QRCodePath) throws Exception {
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(String.valueOf(file));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        pdf.addNewPage();

            // Creating a PdfDocument
            PdfDocument pdfDoc = new PdfDocument(writer);

        Image qrImage = new Image(ImageDataFactory.create(QRCodePath)).setWidth(150).setHeight(150);
        document.add(qrImage);

            // Creating a Document
            Document document = new Document(pdfDoc);

            // Closing the document
            document.close();
            System.out.println("PDF Created");
        }
    }
