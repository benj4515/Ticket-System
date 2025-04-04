package dk.easv.ticket_system.BLL.Util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class PDFHandler {
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
