package dk.easv.ticket_system;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Objects;

public class EventController {


    @FXML
    public FlowPane flowPane;
    @FXML
    private BorderPane bpnTopBar;
    @FXML
    private BorderPane bpnFullPane;

    private boolean listenersAdded = false;

    private double width;


    public void initialize() {
        // TODO: Something


        //Forces resizing of the window because scenebuilder is fucking with me
        if (!listenersAdded) {
            Stage primaryStage = (Stage) Window.getWindows().get(0);
            width = primaryStage.getWidth();
            bpnFullPane.setPrefWidth(width - 256);

            primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
                width = newVal.doubleValue();
                bpnFullPane.setPrefWidth(width - 256);
                System.out.println("Width: " + width); //TODO: Should not run multiple times
            });

            listenersAdded = true;
        }

        pane1();
        pane2();
    }
    public void pane1(){
        Pane customPane1 = new Pane();
        customPane1.setPrefSize(460, 485);
        flowPane.getChildren().add(customPane1);
        customPane1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        VBox vbox1 = new VBox();
        customPane1.getChildren().add(vbox1);
        Label label1 = new Label("Summer music festival");
        label1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 16px;");
        vbox1.getChildren().add(label1);


    }

    public void pane2(){
        Pane customPane2 = new Pane();
        customPane2.setPrefSize(460, 485);
        flowPane.getChildren().add(customPane2);
        customPane2.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        VBox vbox2 = new VBox();
        customPane2.getChildren().add(vbox2);
        Label label2 = new Label("Tech conference");
        label2.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 16px;");
        vbox2.getChildren().add(label2);


    }
}
