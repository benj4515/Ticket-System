package dk.easv.ticket_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Object parent;



    public void initialize(){

    }

    @FXML
    public void onUserManagement(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("UserPane.fxml"));
        pnePane.getChildren().setAll(pane);
    }

    @FXML
    public void onEventManagement(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("EventPane.fxml"));
        pnePane.getChildren().setAll(pane);
    }

    @FXML
    public void onUniversalTicket(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("U-TicketPane.fxml"));
        pnePane.getChildren().setAll(pane);
    }

    @FXML
    public void onCheckout(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("CheckoutPane.fxml"));
        pnePane.getChildren().setAll(pane);
    }
}
