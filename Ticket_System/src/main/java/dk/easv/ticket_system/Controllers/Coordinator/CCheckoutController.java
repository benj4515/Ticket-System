package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BLL.Util.EmailHandler;
import dk.easv.ticket_system.BLL.Util.QRImageUtil;
import dk.easv.ticket_system.BLL.Util.PDFHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.Random;

public class CCheckoutController {


    public ImageView imvTicketPreview;
    public VBox vbxTicketTypes;
    public Label lblEventStartDate;
    public Label lblEventEndDate;
    public Label lblEventStartTime;
    public Label lblEventLocation;
    public Label lblEventEndTime;
    public Label lblEventDescription;
    public ImageView imvCalenderPreview;
    public ImageView imvTimePreview;
    public ImageView imvLocationPreview;
    public ImageView imvQrPreview;
    public Label lblEventTitle;
    @FXML
    private FlowPane flowPane;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnEmail;

    private CEventDetailsController cEventDetailsController;

    private EmailHandler emailHandler;

    public CCheckoutController() {

    }
    private String Toemail;
    public void setEmail() {
        String inputEmail = txtEmail.getText();
        if (inputEmail != null && inputEmail.contains("@")) {
            this.Toemail = inputEmail;
        }
    }
    public String getEmail() {
        return Toemail;
    }

    @FXML
    private void onbtnEmailclick (ActionEvent actionEvent) throws Exception {
        setEmail();

        String rndString = generateRandomString();
        String qrFilePath = "Ticket_System/src/main/resources/PDFImages/" + rndString + "_qr.PNG";


        BufferedImage qrImage = QRImageUtil.generateQRCode(rndString, 150, 150);

        ImageIO.write(qrImage, "PNG", new File(qrFilePath));

        String ticketPath = "Ticket_System/src/main/resources/PDFs/" + rndString + ".pdf";

        PDFHandler.createPDF(ticketPath,qrFilePath);

        // Forsøg at sende emailen
        new EmailHandler().send("EventHub ticket", """
                    Dear reader,

                    Hello world
                    Best regards,
                    EventHub
                    """, new File(ticketPath), getEmail());

    }
    public void initialize(){
        
    }

    public void setSelectedTickets(ObservableList<TicketType> selectedTickets) {
        System.out.println(selectedTickets + " selectedTickets");
        for (TicketType ticketType : selectedTickets) {
            Pane pane = new Pane();
            pane.setPrefSize(1000, 50);
            vbxTicketTypes.getChildren().add(pane);
            AnchorPane anchorPaneUser1 = new AnchorPane();
            Label labelName1 = new Label(ticketType.getTicketDescription());
            labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
            anchorPaneUser1.getChildren().add(labelName1);
            AnchorPane.setTopAnchor(labelName1, 10.0);
            AnchorPane.setLeftAnchor(labelName1, 68.0);
            Label labelEmail1 = new Label(ticketType.getTicketPrice() + " DKK");
            anchorPaneUser1.getChildren().add(labelEmail1);
            AnchorPane.setTopAnchor(labelEmail1, 30.0);
            AnchorPane.setLeftAnchor(labelEmail1, 68.0);
            pane.getChildren().add(anchorPaneUser1);
        }
    }

    public void setSelectedEvent(Event event){
        lblEventTitle.setText(event.geteventTitle());
        lblEventStartDate.setText(event.geteventStartDate().toString());
        lblEventEndDate.setText(event.getEventEndDate().toString());
        lblEventStartTime.setText(event.geteventStartTime());
        lblEventLocation.setText(event.getLocation());
        lblEventDescription.setText(event.geteventDescription());
        lblEventEndTime.setText(event.geteventEndTime());

        imvQrPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/QrPreview.png"))));
        imvCalenderPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Calender.png"))));
        imvTimePreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Time.png"))));
        imvLocationPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Location.png"))));

    }

    public void HandleOneFreeBeer(ActionEvent actionEvent) {
    }

    public void Handle50OffOneDrink(ActionEvent actionEvent) {

    }

    public void Handle1SetOfFreeEarplugs(ActionEvent actionEvent) {
    }

    public static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        int length = random.nextInt(30) + 15;
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return length + "abc" + randomString.toString();

    }
}
