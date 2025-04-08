/**
 * Controller for the checkout interface in the ticket system.
 * Manages the ticket purchase completion process including displaying selected tickets,
 * ticket previews, and sending confirmation emails with ticket attachments.
 * Provides functionality for handling various ticket types and promotional offers.
 */
package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BLL.Util.EmailHandler;
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

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

public class CCheckoutController {

    public ImageView imvTicketPreview;        // Displays a preview of the ticket layout
    public VBox vbxTicketTypes;               // Container for displaying selected ticket types
    @FXML
    private FlowPane flowPane;                // Main layout container (unused)
    @FXML
    private TextField txtEmail;               // Field for entering recipient email address
    @FXML
    private Button btnEmail;                  // Button to trigger email sending

    private CEventDetailsController cEventDetailsController;  // Reference to the event details controller (unused)
    private EmailHandler emailHandler;        // Utility for handling email operations (unused)
    private String Toemail;                   // Stores the validated recipient email address

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
    private void onbtnEmailclick(ActionEvent actionEvent) throws Exception {
        setEmail();

        // Attempt to send the email with a PDF attachment
        new EmailHandler().send("EventHub ticket", """
                    Dear reader,

                    Hello world
                    Best regards,
                    EventHub
                    """, new File("Ticket_System/src/main/resources/PDFs/sample.pdf"), getEmail());
    }

    /**
     * Initializes the controller state.
     * Loads and displays the ticket preview image.
     */
    public void initialize() {
        imvTicketPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/RealTicketPreview.png"))));
    }

    /**
     * Populates the ticket types container with the selected tickets.
     * Creates a visual representation for each ticket type showing
     * its description and price.
     *
     * @param selectedTickets An observable list of ticket types to display
     */
    public void setSelectedTickets(ObservableList<TicketType> selectedTickets) {
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

    /**
     * Placeholder method for handling the "One Free Beer" promotion.
     * Currently not implemented.
     *
     * @param actionEvent The triggering action event
     */
    public void HandleOneFreeBeer(ActionEvent actionEvent) {
    }

    /**
     * Placeholder method for handling the "50% Off One Drink" promotion.
     * Currently not implemented.
     *
     * @param actionEvent The triggering action event
     */
    public void Handle50OffOneDrink(ActionEvent actionEvent) {
    }

    /**
     * Placeholder method for handling the "Free Set of Earplugs" promotion.
     * Currently not implemented.
     *
     * @param actionEvent The triggering action event
     */
    public void Handle1SetOfFreeEarplugs(ActionEvent actionEvent) {
    }
}