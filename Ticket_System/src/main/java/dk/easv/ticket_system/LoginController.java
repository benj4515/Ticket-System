package dk.easv.ticket_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    Image icon = new Image(getClass().getResourceAsStream("/Images/EASV.png"));
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    private FrameController parent;
    @FXML
    private Label lblLoginError;


    public void setParent(FrameController parentParam) {
        this.parent = parentParam;
    }


    public void Start() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent scene = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(scene));
        stage.setTitle("Login");

        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    private void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        openFrame();
        /*
        if (txtEmail.getText().equalsIgnoreCase("email@example.com") && txtPassword.getText().equals("password")) {
            openFrame();
        } else {
            lblLoginError.setText("Incorrect email or password");
        }
         */
    }

    private void openFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Frame.fxml"));
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
}