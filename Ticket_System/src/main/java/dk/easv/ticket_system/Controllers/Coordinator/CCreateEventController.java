package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BLL.Util.EventManager;
import dk.easv.ticket_system.Models.EventModel;
import dk.easv.ticket_system.Models.TicketModel;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
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
    public ScrollPane spTicketTypeEvent;
    public FlowPane fpAddTicketType;
    private EventModel eventModel;
    private int ticketTypeCounter = 0;

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
        String startTime = txtStartTimeEvent.getText();
        String endTime = txtEndTimeEvent.getText();
        Date endDate = Date.valueOf(dpEndDateEvent.getValue());

        if(eventTitle.isEmpty() || eventDescription.isEmpty() || eventLocation.isEmpty() ||  startTime.isEmpty() || endTime.isEmpty()){
            // Display error message
            System.out.println("Please fill in all fields.");
        } else {
            // Create the event object
             Event event = new Event(eventTitle, eventDescription, eventLocation, startDate, startTime, endTime, endDate);
             eventModel.createEvent(event);
            // Create the event
            System.out.println("Event Created: " + eventTitle);
            // Close the window
            Stage stage = (Stage) btnCreateEvent.getScene().getWindow();
            stage.close();
        }

        for(int i = 0; i < ticketTypeCounter; i++) {

            String TicketName = ((TextField)fpAddTicketType.getChildren().get(i).lookup("#txtTicketName")).getText();
            String TicketDescription = ((TextArea)fpAddTicketType.getChildren().get(i).lookup("#txtTicketDescription")).getText();
            String TicketPrice = ((TextField)fpAddTicketType.getChildren().get(i).lookup("#txtPrice")).getText();

            /*
            if(TicketName.isEmpty() || TicketDescription.isEmpty() || TicketPrice.isEmpty()){
                // Display error message
                System.out.println("Please fill in all fields.");
            } else {
                // Create the event object
                TicketModel ticketModel = new TicketModel();
                ticketModel.createTicketType(event, TicketName, TicketDescription, TicketPrice);
                System.out.println("Ticket Type Created: " + TicketName);
            }
             */

            System.out.println("Ticket Type Created: " + TicketName + " with description: " + TicketDescription + " and price: " + TicketPrice);
        }

    }

    public void HandleCancelEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelEvent.getScene().getWindow();
        stage.close();
    }

    public void HandleBtnAddTicketType(ActionEvent actionEvent) {
        Pane pane = new Pane();
        pane.setPrefSize(522, 270);
        pane.setStyle("-fx-border-color: Black;");

        Label lblTicketName = new Label("Ticket Name");
        lblTicketName.setLayoutX(20);
        lblTicketName.setLayoutY(14);

        TextField txtTicketName = new TextField();
        txtTicketName.setId("txtTicketName");
        txtTicketName.setLayoutX(20);
        txtTicketName.setLayoutY(42);
        txtTicketName.setPrefSize(491, 26);
        txtTicketName.setPromptText("e.g., VIP General Admission, Luxury Seating");

        Label lblTicketDescription = new Label("Ticket Description");
        lblTicketDescription.setLayoutX(20);
        lblTicketDescription.setLayoutY(82);

        TextArea txtTicketDescription = new TextArea();
        txtTicketDescription.setId("txtTicketDescription");
        txtTicketDescription.setLayoutX(20);
        txtTicketDescription.setLayoutY(100);
        txtTicketDescription.setPrefSize(491, 97);
        txtTicketDescription.setPromptText("Describe what's included with this ticket type");

        Label lblPrice = new Label("Price");
        lblPrice.setLayoutX(20);
        lblPrice.setLayoutY(210);

        TextField txtPrice = new TextField();
        txtPrice.setId("txtPrice");
        txtPrice.setLayoutX(15);
        txtPrice.setLayoutY(228);
        txtPrice.setPromptText("0.00 KR.");

        // Create Delete Button
        Button btnDelete = new Button("Delete");
        btnDelete.setLayoutX(420);
        btnDelete.setLayoutY(228);
        btnDelete.setOnAction(e -> {
            fpAddTicketType.getChildren().remove(pane); // Remove the specific ticket pane
            ticketTypeCounter--; // Decrease the counter
            System.out.println("Ticket Type Removed. Remaining: " + ticketTypeCounter);
        });

        pane.getChildren().addAll(lblTicketName, txtTicketName, lblTicketDescription, txtTicketDescription,
                lblPrice, txtPrice, btnDelete);

        fpAddTicketType.getChildren().add(pane); // Add the new pane to the container
        ticketTypeCounter++; // Increment counter
        System.out.println("Ticket Type Added. Total: " + ticketTypeCounter);
    }

}
