package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CEventDetailsController {


    public Button btnEditEvent;
    public Label lblEventDetails;
    private Event event;
    public void setEvent(Event event) {
        lblEventDetails.setText(event.geteventTitle());
        this.event = event;
    }

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
}
