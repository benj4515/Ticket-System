package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.User;
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

public class CFrameController {
    @FXML
    public Pane pnePane;
    @FXML
    public Button btnUserManagement;
    @FXML
    public Button btnEventManagement;
    @FXML
    public Button btnCheckout;

    @FXML
    public AnchorPane apnUser;
    public ImageView imgUserIcon;
    public Label lblUser;
    public AnchorPane apnEvent;
    public ImageView imgEventIcon;
    public Label lblEvent;
    public AnchorPane apnUTicket;
    public ImageView imgUTicketIcon;
    public Label lblUTicket;
    public AnchorPane apnCheckout;
    public ImageView imgCheckoutIcon;
    public Label lblCheckout;
    public Label lblUsername;
    public ImageView imgUser;
    public Label lblEmail;
    private UserModel userModel;
    private User loggedInUser;

    @FXML
    private Object parent;

    public void initialize() {
        try {
            userModel = new UserModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        lblEmail.setText(user.getEmail());
    }


    @FXML
    private void resetStyles() {
        apnUser.setStyle("-fx-background-color: white");
        lblUser.setStyle("-fx-text-fill: black");
        apnEvent.setStyle("-fx-background-color: white");
        lblEvent.setStyle("-fx-text-fill: black");
        //apnUTicket.setStyle("-fx-background-color: white");
        //lblUTicket.setStyle("-fx-text-fill: black");
        apnCheckout.setStyle("-fx-background-color: white");
        lblCheckout.setStyle("-fx-text-fill: black");

    }

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
    public void onCheckout(ActionEvent actionEvent) throws IOException {
        resetStyles();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CCheckoutPane.fxml"));
        Pane pane = loader.load();
        CCheckoutController checkoutController = loader.getController();
        pnePane.getChildren().setAll(pane);
        apnCheckout.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblCheckout.setStyle("-fx-text-fill: #1D4ED8");
    }
}
