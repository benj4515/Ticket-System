package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

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

    public void initialize() {

    }

    public void HandleBtnAddTicketType(ActionEvent actionEvent) {
    }

    public void HandleCancelEvent(ActionEvent actionEvent) {
    }

    public void HandleEditEvent(ActionEvent actionEvent) {

    }

    public void setEvent(Event event) {
        txtEventTitle.setText(event.geteventTitle());
        txtDescribeEvent.setText(event.geteventDescription());
        txtLocationEvent.setText(event.getLocation());
        dpStartDateEvent.setValue(event.geteventStartDate().toLocalDate());
        dpEndDateEvent.setValue(event.getEventEndDate().toLocalDate());
        txtStartTimeEvent.setText(event.geteventStartTime());
        txtEndTimeEvent.setText(event.geteventEndTime());
    }
}
