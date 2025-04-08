/**
 * Controller for the event editing interface in the ticket system.
 * Manages the form for updating existing events including event details, dates, times,
 * and associated ticket types. Provides functionality for modifying event properties
 * while maintaining the event's identity in the system.
 */
package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.Models.EventModel;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.sql.Date;

public class CEditEventController {
    // Event details form fields
    public TextField txtEventTitle;           // Field for editing event title
    public TextArea txtDescribeEvent;         // Field for editing event description
    public TextField txtLocationEvent;        // Field for editing event location
    public DatePicker dpStartDateEvent;       // Date picker for editing event start date
    public DatePicker dpEndDateEvent;         // Date picker for editing event end date
    public TextField txtStartTimeEvent;       // Field for editing event start time
    public TextField txtEndTimeEvent;         // Field for editing event end time

    // Action buttons
    public Button btnAddTicketTypeEvent;      // Button to add a new ticket type form
    public ScrollPane spTicketTypeEvent;      // Scrollable container for ticket type forms
    public FlowPane fpAddTicketType;          // Flow layout for dynamic ticket type forms
    public Button btnCancelEvent;             // Button to cancel event editing
    public Button btnEditEvent;               // Button to submit and update the event

    // Model for data operations
    private EventModel eventModel;            // Model for event data operations

    // Event data fields
    private String eventTitle;                // Stores the updated event title
    private String eventDescription;          // Stores the updated event description
    private String eventLocation;             // Stores the updated event location
    private Date startDate;                   // Stores the updated event start date
    private String startTime;                 // Stores the updated event start time
    private String endTime;                   // Stores the updated event end time
    private Date endDate;                     // Stores the updated event end date
    private Event event;                      // Reference to the original event being edited

    /**
     * Initializes the controller.
     * Currently a stub method with no implementation.
     */
    public void initialize() {
    }

    /**
     * Constructs the controller and initializes the event model.
     * Sets up access to the event data layer for persistence operations.
     */
    public CEditEventController() {
        try{
            eventModel = new EventModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Placeholder method for handling adding ticket types to the event.
     * Currently not implemented.
     *
     * @param actionEvent The triggering action event
     */
    public void HandleBtnAddTicketType(ActionEvent actionEvent) {
    }

    /**
     * Placeholder method for handling event edit cancellation.
     * Currently not implemented.
     *
     * @param actionEvent The triggering action event
     */
    public void HandleCancelEvent(ActionEvent actionEvent) {
    }

    /**
     * Handles the event update process when the Edit button is clicked.
     * Validates input fields, collects updated event data,
     * then persists the changes to the database.
     *
     * @param actionEvent The action event
     * @throws Exception If there's an error during event update
     */
    public void HandleEditEvent(ActionEvent actionEvent) throws Exception {
        // Gather updated event details from form fields
        eventTitle = txtEventTitle.getText();
        eventDescription = txtDescribeEvent.getText();
        eventLocation = txtLocationEvent.getText();
        startDate = Date.valueOf(dpStartDateEvent.getValue());
        startTime = txtStartTimeEvent.getText();
        endTime = txtEndTimeEvent.getText();
        endDate = Date.valueOf(dpEndDateEvent.getValue());
        int eventId = event.getEventID();

        // Validate required event fields
        if (eventTitle.isEmpty() || eventDescription.isEmpty() || eventLocation.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Create updated event object with form data, preserving the original ID
        Event updatedEvent = new Event(eventId, eventTitle, startDate, eventLocation, eventDescription, startTime, endTime, endDate);

        // Save updated event to database
        eventModel.updateEvent(updatedEvent);

        // Close the edit event window
        Stage stage = (Stage) btnEditEvent.getScene().getWindow();
        stage.close();
    }

    /**
     * Populates the form fields with the existing event data for editing.
     * Sets all text fields, date pickers and time fields with values from the event object.
     *
     * @param event The event object to be edited
     */
    public void setEvent(Event event) {
        txtEventTitle.setText(event.geteventTitle());
        txtDescribeEvent.setText(event.geteventDescription());
        txtLocationEvent.setText(event.getLocation());
        dpStartDateEvent.setValue(event.geteventStartDate().toLocalDate());
        dpEndDateEvent.setValue(event.getEventEndDate().toLocalDate());
        txtStartTimeEvent.setText(event.geteventStartTime());
        txtEndTimeEvent.setText(event.geteventEndTime());
        this.event = event;
    }
}