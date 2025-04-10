/**
 * Controller for the login interface in the ticket system.
 * Manages user authentication, validates credentials, and redirects users to appropriate
 * interfaces based on their role (Admin or Coordinator). Handles login form interactions,
 * including password visibility toggling, error messaging, and keyboard shortcuts.
 * Establishes a user session upon successful authentication.
 */
package dk.easv.ticket_system.Controllers;

import dk.easv.ticket_system.BLL.Util.UserSession;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.LoginValidator;
import dk.easv.ticket_system.Controllers.Admin.AFrameController;
import dk.easv.ticket_system.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    public TextField txtShowPassword;           // Field for displaying password in plain text
    Image icon = new Image(getClass().getResourceAsStream("/Images/EASV.png"));  // Application icon
    @FXML
    private Button btnLogin;                    // Login button

    @FXML
    private TextField txtEmail;                 // Email input field
    @FXML
    private TextField txtPassword;              // Password input field (masked)
    private AFrameController parent;            // Reference to parent controller
    @FXML
    private Label lblLoginError;                // Error message display label
    private LoginValidator loginValidator;      // Service for validating login credentials
    private UserSession userSession;            // Manages the current user session
    private AFrameController aFrameController;  // Reference to admin frame controller

    private UserModel userModel;                // Model for accessing user data

    /**
     * Gets the email text field.
     * Provides access to the email input field for testing or external manipulation.
     *
     * @return The email TextField object
     */
    public TextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * Sets the parent controller.
     * Establishes a reference to the parent controller for navigation purposes.
     *
     * @param parentParam The parent controller to set
     */
    public void setParent(AFrameController parentParam) {
        this.parent = parentParam;
    }

    /**
     * Constructs the controller and initializes required models and services.
     * Sets up the user model and session management for login operations.
     * Catches and displays any initialization errors.
     */
    public LoginController() {
        try {
            userModel = new UserModel();
            userSession = new UserSession();
            aFrameController = new AFrameController();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
        System.out.println(userModel.getObservableUsers());
    }

    /**
     * Initializes the controller after FXML fields are populated.
     * Sets up event handlers for keyboard actions including:
     * - Enter key on password field to trigger login
     * - Development shortcuts (UP/DOWN keys) for bypassing login
     * Also configures initial visibility of password fields.
     */
    @FXML
    public void initialize() {
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    onLoginButtonClick(null);
                } catch (IOException e) {
                    displayError(e);
                }
            }
        });

        txtShowPassword.setVisible(false);

        // TODO: Remove this function later
        txtEmail.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DOWN) {
                try {
                    openCoordinatorFrame();
                } catch (IOException e) {
                    displayError(e);
                }
            } else if (event.getCode() == KeyCode.UP) {
                try {
                    openAdminFrame();
                } catch (IOException e) {
                    displayError(e);
                }
            }
        });
    }

    /**
     * Displays an error dialog with the exception message.
     * Creates and shows a modal alert window with error details.
     *
     * @param e The exception containing the error message to display
     */
    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }

    /**
     * Starts the login window as a separate modal dialog.
     * Creates and displays the login interface with appropriate
     * title, icon, and modal behavior.
     *
     * @throws IOException If the login view FXML cannot be loaded
     */
    public void Start() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Login.fxml"));
        Parent scene = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(scene));
        stage.setTitle("Login");

        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Handles the login button click event.
     * Validates user credentials and navigates to the appropriate interface
     * based on the user's role (Admin or Coordinator). Displays error message
     * for invalid credentials.
     *
     * @param actionEvent The button click event (can be null when triggered by Enter key)
     * @throws IOException If there's an error loading the frame view
     */
    @FXML
    private void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        this.loginValidator = new LoginValidator();
        boolean success = loginValidator.validateLogin(txtEmail.getText(), txtPassword.getText());

        if (success && loginValidator.isAdmin(txtEmail.getText())) {
            openAdminFrame();
        } else if (success && loginValidator.isEventCoordinator(txtEmail.getText())) {
            openCoordinatorFrame();
        } else {
            lblLoginError.setText("Incorrect email or password");
        }
        User loggedInUser = UserSession.getLoggedInUser();
        if (loggedInUser != null) {
            System.out.println(loggedInUser);
        } else {
            System.out.println("Wrong");
        }
    }

    /**
     * Opens the admin interface frame.
     * Loads the admin view, configures the window, and closes the login window.
     * Called when a validated user has admin privileges.
     *
     * @throws IOException If the admin view FXML cannot be loaded
     */
    private void openAdminFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Admin/AFrame.fxml"));
        Parent scene = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(scene));
        stage.setTitle("EASV EventHub");
        stage.getIcons().add(icon);
        stage.setMaximized(true);
        stage.show();

        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Opens the coordinator interface frame.
     * Loads the coordinator view, configures the window, and closes the login window.
     * Called when a validated user has coordinator privileges.
     *
     * @throws IOException If the coordinator view FXML cannot be loaded
     */
    private void openCoordinatorFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/dk/easv/ticket_system/Coordinator/CFrame.fxml"));
        Parent scene = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(scene));
        stage.setTitle("EASV EventHub");
        stage.getIcons().add(icon);
        stage.setMaximized(true);
        stage.show();

        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Toggles password visibility between masked and plain text.
     * Switches between the password field and the plain text field
     * to allow users to verify their input.
     *
     * @param actionEvent The button click event
     */
    public void HandleShowPWD(ActionEvent actionEvent) {
        if(!txtShowPassword.isVisible()) {
            txtShowPassword.setVisible(true);
            txtShowPassword.setText(txtPassword.getText());
        } else if (txtShowPassword.isVisible()) {
            txtShowPassword.setVisible(false);
        }
    }
}