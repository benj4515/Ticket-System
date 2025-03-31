package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BLL.Util.EventManager;
import dk.easv.ticket_system.Models.EventModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Date;



public class CCreateEventController {
    public TextField txtEventTitle;
    public TextArea txtDescribeEvent;
    public TextField txtLocationEvent;
    public DatePicker dpStartDateEvent;
    public DatePicker dpEndDateEvent;
    public TextField txtStartTimeEvent;
    public TextField txtEndTimeEvent;
    public Button btnAddTicketTypeEvent;
    public Button btnCancelEvent;
    public Button btnCreateEvent;
    private EventModel eventModel;

    public CCreateEventController() {
        try {
            eventModel = new EventModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }



    private void displayError(Exception e) {
    }


    public void HandleCreateEvent(ActionEvent actionEvent) throws Exception {
        String eventTitle = txtEventTitle.getText();
        String eventDescription = txtDescribeEvent.getText();
        String eventLocation = txtLocationEvent.getText();
        Date startDate = Date.valueOf(dpStartDateEvent.getValue());
        Date endDate = Date.valueOf(dpEndDateEvent.getValue());
        String startTime = txtStartTimeEvent.getText();
        String endTime = txtEndTimeEvent.getText();

        if(eventTitle.isEmpty() || eventDescription.isEmpty() || eventLocation.isEmpty() ||  startTime.isEmpty() || endTime.isEmpty()){
            // Display error message
            System.out.println("Please fill in all fields.");
        } else {
            // Create the event object
             Event event = new Event(eventTitle, eventDescription, eventLocation, startDate, endDate, startTime, endTime);
             eventModel.createEvent(event);
            // Create the event
            System.out.println("Event Created: " + eventTitle);
            // Close the window
            Stage stage = (Stage) btnCreateEvent.getScene().getWindow();
            stage.close();
        }
    }

    public void HandleCancelEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelEvent.getScene().getWindow();
        stage.close();
    }
}
