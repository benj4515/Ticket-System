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

        ImageView imageViewEvent = new ImageView();
        imageViewEvent.setFitHeight(260);
        imageViewEvent.setFitWidth(customPane1.getPrefWidth());
        imageViewEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/TechFest.png"))));
        vbox1.getChildren().add(imageViewEvent);

        Label label1 = new Label("Summer music festival");
        label1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000FF; -fx-padding: 16px;");
        vbox1.getChildren().add(label1);


        Label label2 = new Label("July 15, 2025");
        label2.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000FF; -fx-padding: 10px;");
        vbox1.getChildren().add(label2);

        Label label3 = new Label("7:00 PM");
        label3.setStyle("-fx-font-size: 14px; -fx-text-fill: #E000000FF; -fx-padding: 10px;");
        vbox1.getChildren().add(label3);

        Label label4 = new Label("Central Park, New York");
        label4.setStyle("-fx-font-size: 14px;  -fx-text-fill: #000000FF; -fx-padding: 10px;");
        vbox1.getChildren().add(label4);
        
        Label label5 = new Label("From $59");
        label5.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;  -fx-text-fill: #4F46E5; -fx-padding: 10px;");
        vbox1.getChildren().add(label5);

    }

    public void pane2(){
        Pane customPane2 = new Pane();
        customPane2.setPrefSize(460, 485);
        flowPane.getChildren().add(customPane2);
        customPane2.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");

        VBox vbox2 = new VBox();
        customPane2.getChildren().add(vbox2);

        ImageView imageViewEvent2 = new ImageView();
        imageViewEvent2.setFitHeight(260);
        imageViewEvent2.setFitWidth(customPane2.getPrefWidth());
        imageViewEvent2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Partayy.jpg"))));
        vbox2.getChildren().add(imageViewEvent2);

        Label blabel = new Label("Tech conference");
        blabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000FF; -fx-padding: 12px;");
        vbox2.getChildren().add(blabel);
        Label label1 = new Label("August 20, 2025");
        label1.setStyle("-fx-font-size: 14px;  -fx-text-fill: #000000FF; -fx-padding: 10px;");
        vbox2.getChildren().add(label1);

        Label blabel2 = new Label("9.00AM");
        blabel2.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000FF; -fx-padding: 10px;");
        vbox2.getChildren().add(blabel2);

        Label blabel3 = new Label("Convention Center");
        blabel3.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000FF; -fx-padding: 10px;");
        vbox2.getChildren().add(blabel3);

        Label blabel4 = new Label("From $299");
        blabel4.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #4F46E5; -fx-padding: 10px;");
        vbox2.getChildren().add(blabel4);
    }
}
