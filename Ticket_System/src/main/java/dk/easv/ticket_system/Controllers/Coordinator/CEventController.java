package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.Models.EventModel;
import dk.easv.ticket_system.BE.Event;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        showEvents();
        pane2();
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
        label1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-padding: 16px;");
        vbox1.getChildren().add(label1);

        Label label2 = new Label(event.geteventStartDate().toString());
        label2.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000; -fx-padding: 10px;");
        vbox1.getChildren().add(label2);

        Label label3 = new Label(event.geteventStartTime() + " - " + event.geteventEndTime());
        label3.setStyle("-fx-font-size: 14px; -fx-text-fill: #E000000; -fx-padding: 10px;");
        vbox1.getChildren().add(label3);

        Label label4 = new Label(event.getLocation());
        label4.setStyle("-fx-font-size: 14px;  -fx-text-fill: #000000; -fx-padding: 10px;");
        vbox1.getChildren().add(label4);

        Label label5 = new Label("ticket price"); // TODO: Add price price of the cheapest access ticket
        label5.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;  -fx-text-fill: #4F46E5; -fx-padding: 10px;");
        vbox1.getChildren().add(label5);

        /*
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
         */
        }
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
