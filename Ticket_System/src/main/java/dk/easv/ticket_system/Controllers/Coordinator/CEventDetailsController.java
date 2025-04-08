/**
 * Controller for the event details interface in the ticket system.
 * Displays comprehensive information about a selected event, including title, dates,
 * times, location, and description. Provides functionality for editing events,
 * viewing available ticket types, selecting tickets with quantity controls,
 * and proceeding to checkout for ticket purchase.
 */
package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BLL.Util.TicketTypeManager;
import dk.easv.ticket_system.Models.TicketTypeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CEventDetailsController {

    // Navigation and action buttons
    public Button btnEditEvent;              // Button to open the event edit form
    public Button btnBackToEvents;           // Button to return to events list
    public Button btnAddToCheckout;          // Button to proceed to checkout with selected tickets

    // Event details labels - first set (possibly for main display)
    public Label lblEventTitle1;             // Main display of event title
    public Label lblEventStartDate1;         // Main display of event start date
    public Label lblEventEndDate1;           // Main display of event end date
    public Label lblEventStartTime1;         // Main display of event start time
    public Label lblEventEndTime1;           // Main display of event end time
    public Label lblEventLocation1;          // Main display of event location
    public Label lblEventDescription1;       // Main display of event description

    // Event details labels - second set (possibly for secondary display)
    public Label lblEventTitle;              // Secondary display of event title
    public Label lblEventStartDate;          // Secondary display of event start date
    public Label lblEventEndDate;            // Secondary display of event end date
    public Label lblEventStartTime;          // Secondary display of event start time
    public Label lblEventEndTime;            // Secondary display of event end time
    public Label lblEventLocation;           // Secondary display of event location
    public Label lblEventDescription;        // Secondary display of event description

    // Container for ticket types
    public VBox vbxTicketTypes;              // Vertical container for displaying ticket types

    // Visual elements and icons
    public ImageView imvQrPreview;           // Preview of QR code for event/tickets
    public ImageView imvEventImage;          // Event promotional image
    public ImageView imvBarPreview;          // Preview of barcode for event/tickets
    public ImageView imvCalenderPreview;     // Calendar icon for the first set of labels
    public ImageView imvTimePreview;         // Time icon for the first set of labels
    public ImageView imvLocationPreview;     // Location icon for the first set of labels
    public ImageView imvCalenderPreview1;    // Calendar icon for the second set of labels
    public ImageView imvTimePreview1;        // Time icon for the second set of labels
    public ImageView imvLocationPreview1;    // Location icon for the second set of labels

    // Layout container
    public AnchorPane apPane;                // Main container for the event details view

    // Data and state objects
    private Event event;                     // The event being displayed
    private TicketTypeModel ticketTypeModel; // Model for accessing ticket type data
    private Map<Integer, Integer> ticketCounts = new HashMap<>();  // Tracks quantity of each selected ticket type

    /**
     * Constructs the controller and initializes the ticket type model.
     * Sets up access to ticket data for the current event.
     */
    public CEventDetailsController() {
        try{
            ticketTypeModel = new TicketTypeModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the event to display and populates the UI with its details.
     * Displays event information in both sets of labels, loads appropriate
     * images and icons, and populates the ticket types list.
     *
     * @param event The event object to display
     * @throws Exception If there's an error retrieving ticket types for the event
     */
    public void setEvent(Event event) throws Exception {
        // Set event details in the first set of labels
        lblEventTitle.setText(event.geteventTitle());
        lblEventStartDate.setText(event.geteventStartDate().toString());
        lblEventEndDate.setText(event.getEventEndDate().toString());
        lblEventStartTime.setText(event.geteventStartTime());
        lblEventLocation.setText(event.getLocation());
        lblEventDescription.setText(event.geteventDescription());
        lblEventEndTime.setText(event.geteventEndTime());

        // Set event details in the second set of labels
        lblEventTitle1.setText(event.geteventTitle());
        lblEventStartDate1.setText(event.geteventStartDate().toString());
        lblEventEndDate1.setText(event.getEventEndDate().toString());
        lblEventStartTime1.setText(event.geteventStartTime());
        lblEventLocation1.setText(event.getLocation());
        lblEventDescription1.setText(event.geteventDescription());
        lblEventEndTime1.setText(event.geteventEndTime());

        // Store the event object
        this.event = event;

        // Load and set images for the event display
        imvEventImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/TechFest.png"))));
        imvQrPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/QrPreview.png"))));
        imvBarPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/barcodePreview.png"))));

        // Load and set icons for the first set of labels
        imvCalenderPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Calender.png"))));
        imvTimePreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Time.png"))));
        imvLocationPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Location.png"))));

        // Load and set icons for the second set of labels
        imvCalenderPreview1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Calender.png"))));
        imvTimePreview1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Time.png"))));
        imvLocationPreview1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Location.png"))));

        // Display ticket types for this event
        ShowTicketTypes();
        System.out.println(event.getEventID());
    }

    /**
     * Handles the Edit Event button click.
     * Opens a modal dialog with the event editing form and passes 
     * the current event data to be modified.
     *
     * @param actionEvent The action event
     * @throws IOException If there's an error loading the edit form
     */
    public void HandleBtnEditEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CEditEvent.fxml"));
        Parent root = loader.load();
        CEditEventController controller = loader.getController();
        controller.setEvent(event);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Edit Event");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Displays available ticket types for the current event.
     * Creates a visual representation for each ticket type showing its description,
     * price, and a color-coded background. Adds click handlers to manage ticket
     * selection with quantity tracking.
     *
     * @throws Exception If there's an error retrieving ticket types
     */
    private void ShowTicketTypes() throws Exception {
        for (TicketType ticketType : ticketTypeModel.getObservableTicketTypes()) {
            if (ticketType.getEventID() == event.getEventID()) {
                // Create button for each ticket type with gradient background using ticket type color
                Button button1 = new Button();
                button1.setPrefSize(460, 75);

                String ticketColor = ticketType.getTicketTypeColor() != null ?
                        ticketType.getTicketTypeColor() : "#FFF";

                button1.setStyle("-fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0; -fx-background-color: linear-gradient(to left, #FFF, " + ticketColor + ");");                System.out.printf("TicketType color: %s%n", ticketType.getTicketTypeColor());
                vbxTicketTypes.getChildren().add(button1);

                // Set up layout for ticket type button
                AnchorPane anchorPaneUser1 = new AnchorPane();
                button1.setGraphic(anchorPaneUser1);

                // Display ticket description
                Label labelName1 = new Label(ticketType.getTicketDescription());
                labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
                anchorPaneUser1.getChildren().add(labelName1);
                AnchorPane.setTopAnchor(labelName1, 10.0);
                AnchorPane.setLeftAnchor(labelName1, 68.0);

                // Display ticket icon
                ImageView imageViewUser1 = new ImageView();
                imageViewUser1.setFitHeight(50.0);
                imageViewUser1.setFitWidth(50.0);
                anchorPaneUser1.getChildren().add(imageViewUser1);
                imageViewUser1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/festival.png"))));
                AnchorPane.setTopAnchor(imageViewUser1, 8.0);
                AnchorPane.setLeftAnchor(imageViewUser1, 6.0);

                // Display ticket price
                Label labelEmail1 = new Label(ticketType.getTicketPrice() + " DKK");
                anchorPaneUser1.getChildren().add(labelEmail1);
                AnchorPane.setTopAnchor(labelEmail1, 30.0);
                AnchorPane.setLeftAnchor(labelEmail1, 68.0);

                // Initialize ticket count in the tracking map
                ticketCounts.put(ticketType.getTicketTypeID(), 0);

                // Add visual elements to display quantity selected
                Circle countCircle = new Circle(15, javafx.scene.paint.Color.BLUE);
                countCircle.setVisible(false);
                anchorPaneUser1.getChildren().add(countCircle);
                AnchorPane.setTopAnchor(countCircle, 5.0);
                AnchorPane.setRightAnchor(countCircle, 5.0);

                Label countLabel = new Label("0");
                countLabel.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold; -fx-font-size: 14px; -fx-alignment: center;");
                countLabel.setVisible(false);
                anchorPaneUser1.getChildren().add(countLabel);
                AnchorPane.setTopAnchor(countLabel, 10.0);
                AnchorPane.setRightAnchor(countLabel, 15.0);

                // Add event handlers for mouse clicks
                // Left click: increase ticket quantity
                // Right click: decrease ticket quantity
                button1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        // Left click: increase count
                        int count = ticketCounts.get(ticketType.getTicketTypeID());
                        ticketCounts.put(ticketType.getTicketTypeID(), count + 1);
                        countLabel.setText("" + (count + 1));
                        countCircle.setVisible(true);
                        countLabel.setVisible(true);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        // Right click: decrease count, minimum 0
                        int count = ticketCounts.get(ticketType.getTicketTypeID());
                        if (count > 0) {
                            ticketCounts.put(ticketType.getTicketTypeID(), count - 1);
                            countLabel.setText("" + (count - 1));
                            if (count - 1 == 0) {
                                countCircle.setVisible(false);
                                countLabel.setVisible(false);
                            }
                        }
                    }
                });
            }
        }
    }

    /**
     * Handles the checkout button click action.
     * Collects all selected ticket types with their quantities,
     * navigates to the checkout view, and passes the selected tickets list.
     *
     * @param actionEvent The action event
     */
    public void HandleBtnProceedToCheckout(ActionEvent actionEvent) {
        // Create list to hold selected tickets
        ObservableList<TicketType> selectedTickets = FXCollections.observableArrayList();
        selectedTickets.clear();

        // Process each ticket type and its selected quantity
        for (Map.Entry<Integer, Integer> entry : ticketCounts.entrySet()) {
            int ticketID = entry.getKey();
            int count = entry.getValue();
            if (count > 0) {
                // Add ticket to the list once for each quantity selected
                TicketType ticketType = ticketTypeModel.getTicketTypeByID(ticketID);
                for (int i = 0; i < count; i++) {
                    selectedTickets.add(ticketType);
                }
            }
        }

        // Debug log selected tickets
        System.out.printf("Selected tickets: %s%n", selectedTickets);

        try {
            // Load checkout view and pass selected tickets
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CCheckoutPane.fxml"));
            Parent root = loader.load();
            CCheckoutController controller = loader.getController();
            controller.setSelectedTickets(selectedTickets);

            // Replace current content with checkout view
            apPane.getChildren().clear();
            apPane.getChildren().add(root);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the back to events button click.
     * Returns to the events list view by replacing the current content.
     *
     * @param actionEvent The action event
     */
    public void HandleBackToEventsButton(ActionEvent actionEvent) {
        try {
            // Load events view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Coordinator/CEventPane.fxml"));
            Parent root = loader.load();

            // Replace current content with events view
            apPane.getChildren().clear();
            apPane.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}