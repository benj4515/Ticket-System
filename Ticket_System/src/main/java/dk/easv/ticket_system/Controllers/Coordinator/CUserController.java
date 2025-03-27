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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CUserController {
    @FXML
    private FlowPane flowPane;
    private UserModel userModel;
    @FXML
    private Pane customPane1;
    @FXML
    private VBox vbox1;
    @FXML
    private Pane customPane2;
    @FXML
    private Pane customPane3;
    @FXML
    private VBox vbox3;
    @FXML
    private Pane customPane4;
    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPhoneNumber;
    @FXML
    private Label lblRole;
    @FXML
    private Label lblName;
    @FXML
    private Label lblUserCreated;
    @FXML
    private Button btnNewUser;
    @FXML
    private ScrollPane scpScrollPane;


    public CUserController() {
        try {
            userModel = new UserModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onNewUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ACreateUser.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("New User");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    @FXML
    public void initialize() {
        showUserList();
        showEventList();

        scpScrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 110);
    }

    private void updateSelectedUser(User user) {
        lblName.setText(user.getFirstName() + " " + user.getLastName());
        lblEmail.setText(user.getEmail());
        lblFirstName.setText(user.getFirstName());
        lblLastName.setText(user.getLastName());
        lblPhoneNumber.setText(user.getPhoneNumber());
        if (user.getRoleID() == 1) {
            lblRole.setText("      Admin");
        } else if (user.getRoleID() == 2 ) {
            lblRole.setText("  Coordinator");
        }
        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getPhoneNumber() + " " + user.getRoleID());
    }

    public void showUserList(){
        for (User user : userModel.getObservableUsers()) {
            Button button1 = new Button();
            button1.setPrefSize(460, 75);
            button1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
            vbox1.getChildren().add(button1);
            AnchorPane anchorPaneUser1 = new AnchorPane();
            button1.setGraphic(anchorPaneUser1);
            Label labelName1 = new Label(user.getFirstName() + " " + user.getLastName());
            labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
            anchorPaneUser1.getChildren().add(labelName1);
            AnchorPane.setTopAnchor(labelName1, 10.0);
            AnchorPane.setLeftAnchor(labelName1, 68.0);
            ImageView imageViewUser1 = new ImageView();
            imageViewUser1.setFitHeight(50.0);
            imageViewUser1.setFitWidth(50.0);
            anchorPaneUser1.getChildren().add(imageViewUser1);
            imageViewUser1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/john.png"))));
            AnchorPane.setTopAnchor(imageViewUser1, 8.0);
            AnchorPane.setLeftAnchor(imageViewUser1, 6.0);
            Label labelEmail1 = new Label(user.getEmail());
            anchorPaneUser1.getChildren().add(labelEmail1);
            AnchorPane.setTopAnchor(labelEmail1, 30.0);
            AnchorPane.setLeftAnchor(labelEmail1, 68.0);

            button1.setOnAction(event -> updateSelectedUser(user));
        }
    }

    public void showEventList(){

        Button button1 = new Button(); // TODO: Make dynamic with a loop later.
        button1.setPrefSize(460, 75);
        button1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        vbox3.getChildren().add(button1);
        AnchorPane anchorPaneUser1 = new AnchorPane();
        button1.setGraphic(anchorPaneUser1);
        Label labelName1 = new Label("Summer Music Festival");
        labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
        anchorPaneUser1.getChildren().add(labelName1);
        AnchorPane.setTopAnchor(labelName1, 10.0);
        AnchorPane.setLeftAnchor(labelName1, 68.0);
        ImageView imageViewUser1 = new ImageView();
        imageViewUser1.setFitHeight(50.0);
        imageViewUser1.setFitWidth(50.0);
        anchorPaneUser1.getChildren().add(imageViewUser1);
        imageViewUser1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/festival.png"))));
        AnchorPane.setTopAnchor(imageViewUser1, 8.0);
        AnchorPane.setLeftAnchor(imageViewUser1, 6.0);
        Label labelEmail1 = new Label("Central Park, New York");
        anchorPaneUser1.getChildren().add(labelEmail1);
        AnchorPane.setTopAnchor(labelEmail1, 30.0);
        AnchorPane.setLeftAnchor(labelEmail1, 68.0);
    }

}
