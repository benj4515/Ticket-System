/**
 * Controller for the main admin panel frame in the ticket system.
 * Provides navigation between different administrative sections including
 * user management, event management, ticket operations, and checkout functionality.
 * Displays and manages the currently logged-in user's information.
 */
package dk.easv.ticket_system.Controllers.Admin;


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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

public class AFrameController {
    private static final Logger log = LoggerFactory.getLogger(AFrameController.class);
    Image icon = new Image(getClass().getResourceAsStream("/Images/EASV.png"));  // Application icon

    @FXML
    public Pane pnePane;                 // Main content pane for loading different views
    @FXML
    public Button btnUserManagement;      // Button for navigating to user management

    @FXML
    public AnchorPane apnUser;            // Container for user management navigation element
    public ImageView imgUserIcon;         // Icon for user management
    public Label lblUser;                 // Label for user management
    private UserModel userModel;          // Model for user data operations


    /**
     * Initializes the controller state.
     * Creates a new UserModel instance for user data operations.
     */
    public void initialize() {
        imgUserIcon.setImage(new Image(getClass().getResourceAsStream("/Images/person.png")));
        try {
            userModel = new UserModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
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