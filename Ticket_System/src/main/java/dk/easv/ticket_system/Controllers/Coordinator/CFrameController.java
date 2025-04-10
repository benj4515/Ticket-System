/**
 * Main navigation controller for the coordinator interface in the ticket system.
 * Manages the primary navigation frame that contains buttons and indicators for different
 * sections (User Management, Event Management, Checkout). Handles navigation between
 * different views and provides visual feedback on the currently selected section.
 * Also displays information about the currently logged-in user.
 */
package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CFrameController {
    // Main content container
    @FXML
    public Pane pnePane;                // Container for currently active view content
    Image icon = new Image(getClass().getResourceAsStream("/Images/EASV.png"));  // Application icon

    // Navigation buttons
    @FXML
    public Button btnUserManagement;    // Button to navigate to user management
    @FXML
    public Button btnEventManagement;   // Button to navigate to event management
    @FXML
    public Button btnCheckout;          // Button to navigate to checkout

    // User Management navigation item elements
    @FXML
    public AnchorPane apnUser;          // Container for user management navigation item
    public ImageView imgUserIcon;       // Icon for user management
    public Label lblUser;               // Label for user management

    // Event Management navigation item elements
    public AnchorPane apnEvent;         // Container for event management navigation item
    public ImageView imgEventIcon;      // Icon for event management
    public Label lblEvent;              // Label for event management

    // Unused navigation item (possibly for user tickets)
    public AnchorPane apnUTicket;       // Container for unused navigation item
    public ImageView imgUTicketIcon;    // Icon for unused navigation item
    public Label lblUTicket;            // Label for unused navigation item

    // Checkout navigation item elements
    public AnchorPane apnCheckout;      // Container for checkout navigation item
    public ImageView imgCheckoutIcon;   // Icon for checkout
    public Label lblCheckout;           // Label for checkout

    // Data models
    private UserModel userModel;        // Model for accessing user data

    @FXML
    private Object parent;              // Reference to parent container (unused)

    /**
     * Initializes the controller.
     * Sets up the user model for data access.
     */
    public void initialize() {
        imgUserIcon.setImage(new Image(getClass().getResourceAsStream("/Images/person.png")));
        imgEventIcon.setImage(new Image(getClass().getResourceAsStream("/Images/celebration.png")));
        try {
            userModel = new UserModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the visual styling of navigation items to the default state.
     * Removes highlighting from all navigation items.
     */
    @FXML
    private void resetStyles() {
        apnUser.setStyle("-fx-background-color: white");
        lblUser.setStyle("-fx-text-fill: black");
        apnEvent.setStyle("-fx-background-color: white");
        lblEvent.setStyle("-fx-text-fill: black");

    }

    /**
     * Handles navigation to the User Management view.
     * Loads the user management pane, highlights the navigation item,
     * and displays the view in the main content area.
     *
     * @param actionEvent The action event
     * @throws IOException If there's an error loading the view
     */
    @FXML
    public void onUserManagement(ActionEvent actionEvent) throws IOException {
        resetStyles();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CUserPane.fxml"));
        Pane pane = loader.load();
        CUserController userController = loader.getController();
        pnePane.getChildren().setAll(pane);
        apnUser.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblUser.setStyle("-fx-text-fill: #1D4ED8");
    }

    /**
     * Handles navigation to the Event Management view.
     * Loads the event management pane, highlights the navigation item,
     * and displays the view in the main content area.
     *
     * @param actionEvent The action event
     * @throws IOException If there's an error loading the view
     */
    @FXML
    public void onEventManagement(ActionEvent actionEvent) throws IOException {
        resetStyles();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CEventPane.fxml"));
        Pane pane = loader.load();
        CEventController CEventController = loader.getController();
        pnePane.getChildren().setAll(pane);
        apnEvent.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblEvent.setStyle("-fx-text-fill: #1D4ED8");
    }



    @FXML
    public void onHandleLogOut(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Login.fxml"));
        try {
            Parent scene = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(scene));
            stage.setTitle("Login");
            stage.getIcons().add(icon);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage currentStage = (Stage) btnUserManagement.getScene().getWindow();
        currentStage.close();
    }
}