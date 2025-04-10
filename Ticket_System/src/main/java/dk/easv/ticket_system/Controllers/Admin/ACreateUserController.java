/**
 * Controller for the admin user creation interface in the ticket system.
 * Manages the UI and business logic for adding new system users with different roles.
 * Handles form validation, password security, and user registration workflows.
 */
package dk.easv.ticket_system.Controllers.Admin;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class ACreateUserController {

    public TextField txtShowPWD;             // Field for displaying password in plain text

    Image icon = new Image(getClass().getResourceAsStream("/Images/EASV.png"));  // Application icon

    @FXML
    private Button btnCreate;                // Button to create new user

    @FXML
    private TextField txtLoginEmail;         // Field for user email address
    @FXML
    private TextField txtLoginPassword;      // Field for secure password entry
    @FXML
    private TextField txtFirstName;          // Field for user's first name
    @FXML
    private TextField txtLastName;           // Field for user's last name
    @FXML
    private CheckBox adminSelect;            // Checkbox to assign admin role
    @FXML
    private CheckBox eventCoordinatorSelect; // Checkbox to assign event coordinator role

    private UserModel userModel;             // Model for user data operations





    /**
     * Initializes the controller state.
     * Hides the password display field by default.
     */
    @FXML
    private void initialize() {
        txtShowPWD.setVisible(false);
    }


    /**
     * Shows an alert dialog with custom title and message.
     *
     * @param title The alert dialog title
     * @param message The message to display
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Legacy method for menu button user type selection (commented out)

    /**
     * Handles the user creation process when the Create button is clicked.
     * Validates form input, hashes the password, creates a new user,
     * and closes the dialog on success.
     *
     * @param event The action event
     * @throws Exception If there's an error during user creation
     */
    @FXML
    private void handleCreateUser(ActionEvent event) throws Exception {
        String email = txtLoginEmail.getText();
        String password = BCrypt.withDefaults().hashToString(12,txtLoginPassword.getText().toCharArray());
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        int role = 0;
        if (adminSelect.isSelected()) {
            role = 1;
        } else if (eventCoordinatorSelect.isSelected()) {
            role = 2;
        }

        // Debug output code (commented out)

        if (email.isEmpty() || password.isEmpty() || role == 0 || firstName.isEmpty() || lastName.isEmpty()) {
            showAlert("Validation Error", "Please fill in all fields.");
            return;
        }
        User newUser = new User(email, password, role, firstName, lastName);
        userModel.createUser(newUser);

        Stage stage = (Stage) btnCreate.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the cancel action, closing the dialog without creating a user.
     *
     * @param event The action event
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        Stage stage = (Stage) btnCreate.getScene().getWindow();
        stage.close();
    }

    /**
     * Toggles password visibility between masked and plain text.
     *
     * @param actionEvent The action event
     */
    public void HandleShowPassowrd(ActionEvent actionEvent) {
        if (txtLoginPassword.isVisible()) {
            txtLoginPassword.setVisible(false);
            txtShowPWD.setVisible(true);
            txtShowPWD.setText(txtLoginPassword.getText());
        } else {
            txtLoginPassword.setVisible(true);
            txtShowPWD.setVisible(false);
        }
    }
}