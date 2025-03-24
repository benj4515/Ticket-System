package dk.easv.ticket_system;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CreateUserController {
    public TextField txtName;
    private String selectedUserType;
    Image icon = new Image(getClass().getResourceAsStream("/Images/EASV.png"));
    @FXML
    private Label welcomeText;
    @FXML
    private MenuButton btnUserType;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtLoginEmail;
    @FXML
    private TextField txtLoginPassword;
    private FrameController parent;

    private User user;
    private UserModel userModel;


    public void setParent(FrameController parentParam) {
        this.parent = parentParam;
    }

    public CreateUserController() {
        try {
            userModel = new UserModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
        System.out.println(userModel.getObservableUsers());
    }


    private void displayError(Exception e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleUsertype(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selectedText = selectedItem.getText();

        switch (selectedText) {
            case "Admin":
                selectedUserType = "1";
                break;
            case "User":
                selectedUserType = "2";
                break;
            default:
                selectedUserType = "0"; // Default or unknown type
                break;
        }

        btnUserType.setText(selectedText);
    }

    @FXML
    private void handleCreateUser(ActionEvent event) throws Exception {
        String email = txtLoginEmail.getText();
        String password = BCrypt.withDefaults().hashToString(12,txtLoginPassword.getText().toCharArray());
        String role = selectedUserType;

        if (email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            showAlert("Validation Error", "Please fill in all fields.");
        }
        User newUser = new User(email,password,role);
        userModel.createUser(newUser);

        Stage stage = (Stage) btnCreate.getScene().getWindow();
        stage.close();


    }


    @FXML
    private void handleCancel(ActionEvent event) {
        Stage stage = (Stage) btnCreate.getScene().getWindow();
        stage.close();
    }

}

