package dk.easv.ticket_system;

import dk.easv.ticket_system.BLL.Util.UserSession;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.LoginValidator;
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
    private LoginValidator loginValidator;
    private UserSession userSession;


    private UserModel userModel;


    public TextField getTxtEmail() {
        return txtEmail;
    }


    public void setParent(FrameController parentParam) {
        this.parent = parentParam;
    }

    public LoginController() {
        try {
            userModel = new UserModel();
            userSession = new UserSession();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }

    }

    private void displayError(Exception e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
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
        this.loginValidator = new LoginValidator();
        boolean success = loginValidator.validateLogin(txtEmail.getText(), txtPassword.getText());


        if (success && loginValidator.isAdmin(txtEmail.getText())) {
            openAdminFrame();
        } else if (success && loginValidator.isEventCoordinator(txtEmail.getText())) {
            openEventFrame();
            System.out.println(getTxtEmail().getText());
        } else {
            lblLoginError.setText("Incorrect email or password");
        }
        User loggedInUser = UserSession.getLoggedInUser();
        if (loggedInUser != null) {
            System.out.println(loggedInUser);
        } else {
            System.out.println("fuck man ");
        }

    }

    private void openAdminFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminFrame.fxml"));
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

    private void openEventFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventFrame.fxml"));
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