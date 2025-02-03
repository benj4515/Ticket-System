package dk.easv.ticket_system;

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
    private Button btnCheckout;
    @FXML
    private Button btnUniversalTicket;
    @FXML
    private Button btnEventManagement;
    @FXML
    private Button btnUserManagement;
    @FXML
    private Pane pnePane;


    @FXML
    private ImageView imgEASV;
    private Object parent;

    public void initialize() throws IOException {
        //setup();
    }
    private void setup() {
        try{
            // This creates a new FXMLLoader object and loads the NewMovieWindow.fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginController.fxml"));

            // This creates a new parent object and a new stage object. It sets the scene to the parent object and the title to "Add movie"
            Parent scene = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(scene));
            stage.setTitle("Login");

            // Get the controller reference
            LoginController controller = loader.getController();

            // Send a reference to the parent to MyTunesController
            controller.setParent(this); // this refers to this MovieCollectionController object

            // Set the modality to Application (you must close "Add movie" before going to the parent window
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            //displayError(e);
        }

    }




    @FXML
    private void openUserManagement() throws IOException {
        pnePane.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        pnePane.getChildren().setAll(pane);
    }


}
