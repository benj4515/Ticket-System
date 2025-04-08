/**
 * Controller for the event creation interface in the ticket system.
 * Manages the form for creating new events including event details, dates, times,
 * and associated ticket types. Provides functionality for dynamically adding
 * multiple ticket types to an event before creation.
 */
package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BLL.Util.EventManager;
import dk.easv.ticket_system.DAL.EventDAO;
import dk.easv.ticket_system.Models.EventModel;
import dk.easv.ticket_system.Models.TicketModel;
import dk.easv.ticket_system.Models.TicketTypeModel;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CCreateEventController {
    // Event details form fields
    public TextField txtEventTitle;           // Field for event title input
    public TextArea txtDescribeEvent;         // Field for event description
    public TextField txtLocationEvent;        // Field for event location
    public DatePicker dpStartDateEvent;       // Date picker for event start date
    public DatePicker dpEndDateEvent;         // Date picker for event end date
    public TextField txtStartTimeEvent;       // Field for event start time
    public TextField txtEndTimeEvent;         // Field for event end time

    // Action buttons
    public Button btnAddTicketTypeEvent;      // Button to add a new ticket type form
    public Button btnCancelEvent;             // Button to cancel event creation
    public Button btnCreateEvent;             // Button to submit and create the event

    // Container components for ticket types
    public ScrollPane spTicketTypeEvent;      // Scrollable container for ticket type forms
    public FlowPane fpAddTicketType;          // Flow layout for dynamic ticket type forms

    // Models for data operations
    private EventModel eventModel;            // Model for event data operations
    private TicketTypeModel ticketTypeModel;  // Model for ticket type data operations

    // State variables
    private int ticketTypeCounter = 0;        // Tracks number of ticket types added
    private Event event;                      // Reference to the event being created

    /**
     * Constructs the controller and initializes the required models.
     * Sets up event and ticket type models for data operations.
     */
    public CCreateEventController() {
        try {
            eventModel = new EventModel();
            ticketTypeModel = new TicketTypeModel();

        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Displays error messages.
     * Currently a stub method with no implementation.
     *
     * @param e The exception to display
     */
    private void displayError(Exception e) {
    }

    /**
     * Handles the event creation process when the Create button is clicked.
     * Validates input fields, collects event data and ticket types,
     * then persists the new event to the database.
     *
     * @param actionEvent The action event
     * @throws Exception If there's an error during event creation
     */
    public void HandleCreateEvent(ActionEvent actionEvent) throws Exception {
        // Gather event details from form fields
        String eventTitle = txtEventTitle.getText();
        String eventDescription = txtDescribeEvent.getText();
        String eventLocation = txtLocationEvent.getText();
        Date startDate = Date.valueOf(dpStartDateEvent.getValue());
        String startTime = txtStartTimeEvent.getText();
        String endTime = txtEndTimeEvent.getText();
        Date endDate = Date.valueOf(dpEndDateEvent.getValue());

        // Validate required event fields
        if (eventTitle.isEmpty() || eventDescription.isEmpty() || eventLocation.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Create event object with form data
        Event event = new Event(eventTitle, eventDescription, eventLocation, startDate, startTime, endTime, endDate);

        // Collect ticket type data from dynamically created forms
        List<TicketType> ticketTypes = new ArrayList<>();

        for (int i = 0; i < ticketTypeCounter; i++) {
            // Extract ticket type data from form fields
            String ticketName = ((TextField) fpAddTicketType.getChildren().get(i).lookup("#txtTicketName")).getText();
            String ticketDescription = ((TextArea) fpAddTicketType.getChildren().get(i).lookup("#txtTicketDescription")).getText();
            String ticketPriceStr = ((TextField) fpAddTicketType.getChildren().get(i).lookup("#txtPrice")).getText();

            // Validate ticket type fields
            if (ticketName.isEmpty() || ticketDescription.isEmpty() || ticketPriceStr.isEmpty()) {
                System.out.println("Please fill in all fields for tickets.");
            } else {
                ticketTypes.add(new TicketType(ticketName, ticketDescription, Double.parseDouble(ticketPriceStr)));
            }
        }

        // Save event and ticket types to database
        eventModel.createEvent(event, ticketTypes);
        System.out.println("Event and tickets created successfully!");

        // Close the create event window
        Stage stage = (Stage) btnCreateEvent.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the cancel action, closing the event creation window
     * without saving any changes.
     *
     * @param actionEvent The action event
     */
    public void HandleCancelEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelEvent.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles adding a new ticket type form when the Add Ticket Type button is clicked.
     * Dynamically creates a form panel with fields for ticket name, description, 
     * and price, along with a delete button to remove the ticket type.
     *
     * @param actionEvent The action event
     */
    public void HandleBtnAddTicketType(ActionEvent actionEvent) {
        // Create container for the ticket type form
        Pane pane = new Pane();
        pane.setPrefSize(522, 270);
        pane.setStyle("-fx-border-color: Black;");

        // Create and position ticket name label and field
        Label lblTicketName = new Label("Ticket Name");
        lblTicketName.setLayoutX(20);
        lblTicketName.setLayoutY(14);

        TextField txtTicketName = new TextField();
        txtTicketName.setId("txtTicketName");
        txtTicketName.setLayoutX(20);
        txtTicketName.setLayoutY(42);
        txtTicketName.setPrefSize(491, 26);
        txtTicketName.setPromptText("e.g., VIP General Admission, Luxury Seating");

        // Create and position ticket description label and field
        Label lblTicketDescription = new Label("Ticket Description");
        lblTicketDescription.setLayoutX(20);
        lblTicketDescription.setLayoutY(82);

        TextArea txtTicketDescription = new TextArea();
        txtTicketDescription.setId("txtTicketDescription");
        txtTicketDescription.setLayoutX(20);
        txtTicketDescription.setLayoutY(100);
        txtTicketDescription.setPrefSize(491, 97);
        txtTicketDescription.setPromptText("Describe what's included with this ticket type");

        // Create and position price label and field
        Label lblPrice = new Label("Price");
        lblPrice.setLayoutX(20);
        lblPrice.setLayoutY(210);

        TextField txtPrice = new TextField();
        txtPrice.setId("txtPrice");
        txtPrice.setLayoutX(15);
        txtPrice.setLayoutY(228);
        txtPrice.setPromptText("0.00 KR.");

        // Create delete button with handler to remove this ticket type
        Button btnDelete = new Button("Delete");
        btnDelete.setLayoutX(420);
        btnDelete.setLayoutY(228);
        btnDelete.setOnAction(e -> {
            fpAddTicketType.getChildren().remove(pane);
            ticketTypeCounter--;
        });

        // Add all components to the ticket type form panel
        pane.getChildren().addAll(lblTicketName, txtTicketName, lblTicketDescription, txtTicketDescription,
                lblPrice, txtPrice, btnDelete);

        // Add the panel to the flow pane and increment counter
        fpAddTicketType.getChildren().add(pane);
        ticketTypeCounter++;
    }
}