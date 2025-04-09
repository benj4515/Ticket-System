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
    public static void createPDF(String filepath, String QRCodePath) throws Exception {
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(String.valueOf(file));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        pdf.addNewPage();


        Image qrImage = new Image(ImageDataFactory.create(QRCodePath)).setWidth(150).setHeight(150);
        document.add(qrImage);


        document.close();
        System.out.println("PDF Created");
    }
}

