/**
 * Controller for the event management interface in the coordinator view of the ticket system.
 * Displays a grid of event cards with details and images, providing functionality to
 * view event details and create new events. Each event card shows the event title,
 * date, time, and location with appropriate icons.
 */
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
    public FlowPane flowPane;               // Main container for displaying event cards in a grid layout
    private EventModel eventModel;          // Model for accessing event data
    @FXML
    public Button btnCreateEvent;           // Button to trigger event creation
    @FXML
    private BorderPane bpnFullPane;         // Main container for the entire view

    private boolean listenersAdded = false; // Flag to prevent adding multiple listeners
    private double width;                   // Current width of the window
    @FXML
    private ScrollPane scpScrollPane;       // Scrollable container for the event grid

    /**
     * Constructor that initializes the event model.
     * Sets up access to event data through the EventModel.
     */
    public CEventController() {
        try {
            eventModel = new EventModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller.
     * Sets up responsive layout behavior, configures component dimensions,
     * and displays the list of events.
     */
    public void initialize() {
        // TODO: Something

        // Configure window resizing behavior for responsive layout
        if (!listenersAdded) {
            Stage primaryStage = (Stage) Window.getWindows().get(0);
            width = primaryStage.getWidth();
            bpnFullPane.setPrefWidth(width - 256);

            primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
                width = newVal.doubleValue();
                bpnFullPane.setPrefWidth(width - 256);
                System.out.println("Width: " + width); // TODO: Should not run multiple times
            });

            listenersAdded = true;
        }

        // Set dimensions based on screen size
        flowPane.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() - 265);
        flowPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 110);
        scpScrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 110);

        // Display event cards
        showEvents();
    }

    /**
     * Creates and displays visual cards for each event in the model.
     * Each card contains an image, event title, date, time, location, and price information.
     * Cards are styled with shadows and have click handlers to open detailed views.
     */
    public void showEvents() {
        for (Event event : eventModel.getObservableEvents()) {
            // Create container for event card
            Pane customPane1 = new Pane();
            customPane1.setPrefSize(460, 485);
            flowPane.getChildren().add(customPane1);
            customPane1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");

            // Create vertical layout for event content
            VBox vbox1 = new VBox();
            customPane1.getChildren().add(vbox1);

            // Event image
            ImageView imageViewEvent = new ImageView();
            imageViewEvent.setFitHeight(260);
            imageViewEvent.setFitWidth(customPane1.getPrefWidth());
            imageViewEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/TechFest.png"))));
            vbox1.getChildren().add(imageViewEvent);

            // Event title
            Label label1 = new Label(event.geteventTitle());
            label1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-padding: 16px;");
            vbox1.getChildren().add(label1);

            // Event date with calendar icon
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

            // Event time with clock icon
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

            // Event location with map pin icon
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

            // Event price (placeholder)
            Label label5 = new Label("ticket price"); // TODO: Add price price of the cheapest access ticket
            label5.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;  -fx-text-fill: #4F46E5; -fx-padding: 10px;");
            vbox1.getChildren().add(label5);

            // Add click handler to open detailed event view
            customPane1.setOnMouseClicked(event1 -> openSelectedEvent(event));
        }
    }

    /**
     * Opens a detailed view for the selected event.
     * Loads the event details view and passes the selected event to the controller.
     * Replaces the current content with the detailed view.
     *
     * @param event The event to display in detail
     */
    private void openSelectedEvent(Event event) {
        try {
            // Load event details view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CEventDetails.fxml"));
            Parent root = loader.load();

            // Get controller and pass event data
            CEventDetailsController eventDetailsController = loader.getController();
            eventDetailsController.setEvent(event);

            // Replace current view with event details
            bpnFullPane.getChildren().clear();
            bpnFullPane.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the create event button click action.
     * Opens a modal dialog with the event creation form.
     *
     * @param actionEvent The action event
     * @throws IOException If there's an error loading the form
     */
    public void onCreateEvent(ActionEvent actionEvent) throws IOException {
        // Load event creation form
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CCreateEvent.fxml"));
        Parent root = loader.load();

        // Create and configure modal window
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("New Event");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}