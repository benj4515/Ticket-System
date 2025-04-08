/**
 * Controller for the main admin panel frame in the ticket system.
 * Provides navigation between different administrative sections including
 * user management, event management, ticket operations, and checkout functionality.
 * Displays and manages the currently logged-in user's information.
 */
package dk.easv.ticket_system.Controllers.Admin;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.Controllers.Coordinator.CCheckoutController;
import dk.easv.ticket_system.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AFrameController {
    @FXML
    public Pane pnePane;                 // Main content pane for loading different views
    @FXML
    public Button btnUserManagement;      // Button for navigating to user management
    @FXML
    public Button btnEventManagement;     // Button for navigating to event management
    @FXML
    public Button btnUniversalTicket;     // Button for universal ticket operations
    @FXML
    public Button btnCheckout;            // Button for navigating to checkout
    @FXML
    public Button btnNewUser;             // Button for creating a new user

    @FXML
    public AnchorPane apnUser;            // Container for user management navigation element
    public ImageView imgUserIcon;         // Icon for user management
    public Label lblUser;                 // Label for user management
    public AnchorPane apnEvent;           // Container for event management navigation element
    public ImageView imgEventIcon;        // Icon for event management
    public Label lblEvent;                // Label for event management
    public AnchorPane apnUTicket;         // Container for universal ticket navigation element
    public ImageView imgUTicketIcon;      // Icon for universal ticket
    public Label lblUTicket;              // Label for universal ticket
    public AnchorPane apnCheckout;        // Container for checkout navigation element
    public ImageView imgCheckoutIcon;      // Icon for checkout
    public Label lblCheckout;             // Label for checkout
    public Label lblUsername;             // Label for username display
    public ImageView imgUser;             // User avatar image
    public Label lblEmail;                // Label for displaying user email
    public ImageView imgLoggedImg;        // Image for logged-in user
    public Label lblLoggedUser;           // Label showing logged user name
    public Label lblLoggedEmail;          // Label showing logged user email
    private UserModel userModel;          // Model for user data operations
    private User loggedInUser;            // Reference to the currently logged-in user

    @FXML
    private Object parent;                // Reference to parent controller (unused)

    /**
     * Initializes the controller state.
     * Creates a new UserModel instance for user data operations.
     */
    public void initialize() {
        try {
            userModel = new UserModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the currently logged-in user and updates the UI accordingly.
     * Displays the user's email in the appropriate label.
     *
     * @param user The logged-in user object
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        lblEmail.setText(user.getEmail());
    }

    /**
     * Resets the styling of navigation elements to their default state.
     * Used when switching between different views to highlight the active section.
     */
    @FXML
    private void resetStyles() {
        apnUser.setStyle("-fx-background-color: white");
        lblUser.setStyle("-fx-text-fill: black");
    }

    /**
     * Handles navigation to the user management panel.
     * Loads the user management view, resets styles, and highlights
     * the user management navigation element.
     *
     * @param actionEvent The triggering action event
     * @throws IOException If there's an error loading the user management view
     */
    @FXML
    public void onUserManagement(ActionEvent actionEvent) throws IOException {
        resetStyles();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Admin/AUserPane.fxml"));
        Pane pane = loader.load();
        AUserController userController = loader.getController();
        pnePane.getChildren().setAll(pane);
        apnUser.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblUser.setStyle("-fx-text-fill: #1D4ED8");
    }
}