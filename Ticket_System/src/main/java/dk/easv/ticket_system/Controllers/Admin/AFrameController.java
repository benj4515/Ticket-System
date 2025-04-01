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
    public Pane pnePane;
    @FXML
    public Button btnUserManagement;
    @FXML
    public Button btnEventManagement;
    @FXML
    public Button btnUniversalTicket;
    @FXML
    public Button btnCheckout;
    @FXML
    public Button btnNewUser;

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
    public ImageView imgLoggedImg;
    public Label lblLoggedUser;
    public Label lblLoggedEmail;
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
        apnUTicket.setStyle("-fx-background-color: white");
        lblUTicket.setStyle("-fx-text-fill: black");
    }

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
    public void onUniversalTicket(ActionEvent actionEvent) throws IOException {
        resetStyles();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Admin/AU-TicketPane.fxml"));
        Pane pane = loader.load();
        ATicketController ticketController = loader.getController();
        pnePane.getChildren().setAll(pane);
        apnUTicket.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblUTicket.setStyle("-fx-text-fill: #1D4ED8");
    }
}
