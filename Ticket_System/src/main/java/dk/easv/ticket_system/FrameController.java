package dk.easv.ticket_system;

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

public class FrameController {
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
    @FXML
    private Object parent;



    public void initialize(){


    }

    @FXML
    private void resetStyles() {
        apnUser.setStyle("-fx-background-color: white");
        lblUser.setStyle("-fx-text-fill: black");
        apnEvent.setStyle("-fx-background-color: white");
        lblEvent.setStyle("-fx-text-fill: black");
        apnUTicket.setStyle("-fx-background-color: white");
        lblUTicket.setStyle("-fx-text-fill: black");
        apnCheckout.setStyle("-fx-background-color: white");
        lblCheckout.setStyle("-fx-text-fill: black");
    }

    @FXML
    public void onUserManagement(ActionEvent actionEvent) throws IOException {
        resetStyles();
        Pane pane = FXMLLoader.load(getClass().getResource("UserPane.fxml"));
        pnePane.getChildren().setAll(pane);
        apnUser.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblUser.setStyle("-fx-text-fill: #1D4ED8");
    }

    @FXML
    public void onEventManagement(ActionEvent actionEvent) throws IOException {
        resetStyles();
        Pane pane = FXMLLoader.load(getClass().getResource("EventPane.fxml"));
        pnePane.getChildren().setAll(pane);
        apnEvent.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblEvent.setStyle("-fx-text-fill: #1D4ED8");
    }

    @FXML
    public void onUniversalTicket(ActionEvent actionEvent) throws IOException {
        resetStyles();
        Pane pane = FXMLLoader.load(getClass().getResource("U-TicketPane.fxml"));
        pnePane.getChildren().setAll(pane);
        apnUTicket.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblUTicket.setStyle("-fx-text-fill: #1D4ED8");
    }

    @FXML
    public void onCheckout(ActionEvent actionEvent) throws IOException {
        resetStyles();
        Pane pane = FXMLLoader.load(getClass().getResource("CheckoutPane.fxml"));
        pnePane.getChildren().setAll(pane);
        apnCheckout.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 8px");
        lblCheckout.setStyle("-fx-text-fill: #1D4ED8");
    }
}
