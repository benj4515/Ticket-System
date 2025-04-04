package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.Models.EventModel;
import dk.easv.ticket_system.BE.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class CEventController {


    @FXML
    public FlowPane flowPane;
    private EventModel eventModel;
    @FXML
    public Button btnCreateEvent;
    @FXML
    private BorderPane bpnTopBar;
    @FXML
    private BorderPane bpnFullPane;

    private boolean listenersAdded = false;

    private double width;
    @FXML
    private ScrollPane scpScrollPane;

    public CEventController() {
        try {
            eventModel = new EventModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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

        flowPane.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() - 265);
        flowPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 110);
        scpScrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 110);

        showEvents();
    }

    public void showEvents() {
        for (Event event : eventModel.getObservableEvents()) {
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


        Label label1 = new Label(event.geteventTitle());
        label1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-padding: 16px;");
        vbox1.getChildren().add(label1);

        HBox hbox1 = new HBox();
        ImageView imageViewCalender = new ImageView();
        imageViewCalender.setFitHeight(16);
        imageViewCalender.setFitWidth(16);
        imageViewCalender.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Calender.png"))));
        hbox1.getChildren().add(imageViewCalender);
        Label label2 = new Label(event.geteventStartDate().toString());
        label2.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
        hbox1.getChildren().add(label2);
        hbox1.setMaxHeight(16);
        hbox1.setSpacing(5);
        hbox1.setPadding(new Insets(5, 0, 5, 10));
        vbox1.getChildren().add(hbox1);

        HBox hbox2 = new HBox();
        ImageView imageViewTime = new ImageView();
        imageViewTime.setFitHeight(16);
        imageViewTime.setFitWidth(16);
        imageViewTime.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Time.png"))));
        hbox2.getChildren().add(imageViewTime);
        Label label3 = new Label(event.geteventStartTime() + " - " + event.geteventEndTime());
        label3.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
        hbox2.getChildren().add(label3);
        hbox2.setMaxHeight(16);
        hbox2.setSpacing(5);
        hbox2.setPadding(new Insets(5, 0, 5, 10));
        vbox1.getChildren().add(hbox2);

        HBox hbox3 = new HBox();
        ImageView imageViewLocation = new ImageView();
        imageViewLocation.setFitHeight(16);
        imageViewLocation.setFitWidth(16);
        imageViewLocation.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Location.png"))));
        hbox3.getChildren().add(imageViewLocation);
        Label label4 = new Label(event.getLocation());
        label4.setStyle("-fx-font-size: 14px;  -fx-text-fill: #000000;");
        hbox3.getChildren().add(label4);
        hbox3.setMaxHeight(16);
        hbox3.setSpacing(5);
        hbox3.setPadding(new Insets(5, 0, 5, 10));
        vbox1.getChildren().add(hbox3);

        Label label5 = new Label("ticket price"); // TODO: Add price price of the cheapest access ticket
        label5.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;  -fx-text-fill: #4F46E5; -fx-padding: 10px;");
        vbox1.getChildren().add(label5);

        customPane1.setOnMouseClicked(event1 -> openSelectedEvent(event));
        }
    }

    private void openSelectedEvent(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CEventDetails.fxml"));
            Parent root = loader.load();
            CEventDetailsController eventDetailsController = loader.getController();
            eventDetailsController.setEvent(event);
            bpnFullPane.getChildren().clear();
            bpnFullPane.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void onCreateEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CCreateEvent.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("New Event");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
