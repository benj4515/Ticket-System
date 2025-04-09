/**
 * Controller for the checkout interface in the ticket system.
 * Manages the ticket purchase completion process including displaying selected tickets,
 * ticket previews, and sending confirmation emails with ticket attachments.
 * Provides functionality for handling various ticket types and promotional offers.
 */
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
import javafx.scene.control.CheckBox;
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
import java.util.ArrayList;
import java.util.List;
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
    public CheckBox chbOneFreeBeer;
    public CheckBox chb50OffOneDrink;
    public CheckBox chb1SetOfEarplugs;
    @FXML
    private FlowPane flowPane;                // Main layout container (unused)
    @FXML
    private TextField txtEmail;               // Field for entering recipient email address
    @FXML
    private Button btnEmail;    // Button to trigger email sending
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;


    private CEventDetailsController cEventDetailsController;  // Reference to the event details controller (unused)
    private EmailHandler emailHandler;        // Utility for handling email operations (unused)
    private String Toemail;
    private Event event;
    private String customerName;
    private ObservableList<TicketType> selectedTickets; // List of selected ticket types
    private ArrayList<CheckBox> selectedUniversalTickets;
    // Stores the validated recipient email address

    /**
     * Default constructor for the checkout controller.
     */
    public CCheckoutController() {
    }

    /**
     * Validates and sets the recipient email address from the input field.
     * Only accepts email addresses containing the @ symbol.
     */
    public void setEmail() {
        String inputEmail = txtEmail.getText();
        if (inputEmail != null && inputEmail.contains("@")) {
            this.Toemail = inputEmail;
        }
    }
    public void setCustomerName() {
        String inputCustomerName = txtFirstName.getText() + " " + txtLastName.getText();
        this.customerName = inputCustomerName;
    }
    public String getCustomerName() {return customerName;}
    /**
     * Returns the currently set recipient email address.
     *
     * @return The validated email address
     */
    public String getEmail() {
        return Toemail;
    }

    /**
     * Handles the email button click event.
     * Validates the email address and sends a confirmation email
     * with a ticket PDF attachment to the recipient.
     *
     * @param actionEvent The triggering action event
     * @throws Exception If there is an error sending the email
     */


    @FXML
    private void onbtnEmailclick (ActionEvent actionEvent) throws Exception {
        getSelectedUniversalTickets();
        setEmail();
        setCustomerName();

        String rndString = generateRandomString();
        String qrFilePath = "Ticket_System/src/main/resources/PDFImages/" + rndString + "_qr.PNG";


        BufferedImage qrImage = QRImageUtil.generateQRCode(rndString, 150, 150);

        ImageIO.write(qrImage, "PNG", new File(qrFilePath));

        String ticketPath = "Ticket_System/src/main/resources/PDFs/" + rndString + ".pdf";

        PDFHandler.createPDF(ticketPath,qrFilePath, event, selectedTickets, selectedUniversalTickets);

        // Forsøg at sende emailen
        // Attempt to send the email with a PDF attachment
        new EmailHandler().send("Your tickets for " + event.geteventTitle() + " is here", "Hello " + getCustomerName() + "\nYour ticket is attached down bellow" + "\nBest regards EventHub", new File(ticketPath), getEmail());

        new File(qrFilePath).delete();
        new File(ticketPath).delete();
    }

    /**
     * Initializes the controller state.
     * Loads and displays the ticket preview image.
     */
    public void initialize() {

    }

    /**
     * Populates the ticket types container with the selected tickets.
     * Creates a visual representation for each ticket type showing
     * its description and price.
     *
     * @param selectedTickets An observable list of ticket types to display
     */
    public void setSelectedTickets(ObservableList<TicketType> selectedTickets) {
        this.selectedTickets = selectedTickets;
        System.out.println(selectedTickets + " selectedTickets");
        for (TicketType ticketType : selectedTickets) {
            // Create container for each ticket type
            Pane pane = new Pane();
            pane.setPrefSize(1000, 50);
            vbxTicketTypes.getChildren().add(pane);

            // Set up layout for ticket information
            AnchorPane anchorPaneUser1 = new AnchorPane();

            // Display ticket description
            Label labelName1 = new Label(ticketType.getTicketDescription());
            labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
            anchorPaneUser1.getChildren().add(labelName1);
            AnchorPane.setTopAnchor(labelName1, 10.0);
            AnchorPane.setLeftAnchor(labelName1, 68.0);

            // Display ticket price
            Label labelEmail1 = new Label(ticketType.getTicketPrice() + " DKK");
            anchorPaneUser1.getChildren().add(labelEmail1);
            AnchorPane.setTopAnchor(labelEmail1, 30.0);
            AnchorPane.setLeftAnchor(labelEmail1, 68.0);
            pane.getChildren().add(anchorPaneUser1);
        }
    }

    public void setSelectedEvent(Event event){
        this.event = event;
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

    /**
     * Placeholder method for handling the "One Free Beer" promotion.
     * Currently not implemented.
     *
     * @param actionEvent The triggering action event
     */
    public void HandleOneFreeBeer(ActionEvent actionEvent) {
    }

    public void Handle50OffOneDrink(ActionEvent actionEvent) {

    }

    public void Handle1SetOfFreeEarplugs(ActionEvent actionEvent) {
    }

    public void getSelectedUniversalTickets() {
        ArrayList<CheckBox> selectedUniversalTickets = new ArrayList<>();
        if (chbOneFreeBeer.isSelected()) {
            selectedUniversalTickets.add(chbOneFreeBeer);
        }
        if (chb50OffOneDrink.isSelected()) {
            selectedUniversalTickets.add(chb50OffOneDrink);
        }
        if (chb1SetOfEarplugs.isSelected()) {
            selectedUniversalTickets.add(chb1SetOfEarplugs);
        }
        this.selectedUniversalTickets = selectedUniversalTickets;
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
