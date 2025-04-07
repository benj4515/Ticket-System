package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.Models.EventModel;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.sql.Date;

public class CEditEventController {
    public TextField txtEventTitle;
    public TextArea txtDescribeEvent;
    public TextField txtLocationEvent;
    public DatePicker dpStartDateEvent;
    public DatePicker dpEndDateEvent;
    public TextField txtStartTimeEvent;
    public TextField txtEndTimeEvent;
    public Button btnAddTicketTypeEvent;
    public ScrollPane spTicketTypeEvent;
    public FlowPane fpAddTicketType;
    public Button btnCancelEvent;
    public Button btnEditEvent;
    private EventModel eventModel;
    private String eventTitle;
    private String eventDescription;
    private String eventLocation;
    private Date startDate;
    private String startTime;
    private String endTime;
    private Date endDate;
    private Event event;

    public void initialize() {

    }

    public CEditEventController() {

        try{
            eventModel = new EventModel();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void HandleBtnAddTicketType(ActionEvent actionEvent) {
    }

    public void HandleCancelEvent(ActionEvent actionEvent) {
    }

    public void HandleEditEvent(ActionEvent actionEvent) throws Exception {
        eventTitle = txtEventTitle.getText();
        eventDescription = txtDescribeEvent.getText();
        eventLocation = txtLocationEvent.getText();
        startDate = Date.valueOf(dpStartDateEvent.getValue());
        startTime = txtStartTimeEvent.getText();
        endTime = txtEndTimeEvent.getText();
        endDate = Date.valueOf(dpEndDateEvent.getValue());
        int eventId = event.getEventID();

        if (eventTitle.isEmpty() || eventDescription.isEmpty() || eventLocation.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        Event updatedEvent = new Event(eventId,eventTitle, startDate, eventLocation, eventDescription, startTime, endTime, endDate);



        eventModel.updateEvent(updatedEvent);

        Stage stage = (Stage) btnEditEvent.getScene().getWindow();
        stage.close();

    }

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
