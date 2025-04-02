package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.TicketType;
import dk.easv.ticket_system.BLL.Util.TicketTypeManager;
import dk.easv.ticket_system.Models.TicketTypeModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CEventDetailsController {


    public Button btnEditEvent;
    public Label lblEventTitle1;
    public Label lblEventStartDate1;
    public Label lblEventEndDate1;
    public Label lblEventStartTime1;
    public Label lblEventLocation1;
    public Label lblEventDescription1;
    public Label lblEventTitle;
    public Label lblEventStartDate;
    public Label lblEventEndDate;
    public Label lblEventStartTime;
    public Label lblEventLocation;
    public Label lblEventDescription;
    public Label lblEventEndTime1;
    public Label lblEventEndTime;
    public VBox vbxTicketTypes;
    public ImageView imvQrPreview;
    public ImageView imvEventImage;
    private Event event;
    private TicketTypeModel ticketTypeModel;

    public CEventDetailsController() {
        try{
            ticketTypeModel = new TicketTypeModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void setEvent(Event event) throws Exception {
        lblEventTitle.setText(event.geteventTitle());
        lblEventStartDate.setText(event.geteventStartDate().toString());
        lblEventEndDate.setText(event.getEventEndDate().toString());
        lblEventStartTime.setText(event.geteventStartTime());
        lblEventLocation.setText(event.getLocation());
        lblEventDescription.setText(event.geteventDescription());
        lblEventEndTime.setText(event.geteventEndTime());

        lblEventTitle1.setText(event.geteventTitle());
        lblEventStartDate1.setText(event.geteventStartDate().toString());
        lblEventEndDate1.setText(event.getEventEndDate().toString());
        lblEventStartTime1.setText(event.geteventStartTime());
        lblEventLocation1.setText(event.getLocation());
        lblEventDescription1.setText(event.geteventDescription());
        lblEventEndTime1.setText(event.geteventEndTime());
        this.event = event;

        // Set the image for the event
        imvEventImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/TechFest.png"))));
        imvQrPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/QrPreview.png"))));

        ShowTicketTypes();
        System.out.println(event.getEventID());
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



    private void ShowTicketTypes() throws Exception {
        for (TicketType ticketType : ticketTypeModel.getObservableTicketTypes()) {
            if (ticketType.getEventID() == event.getEventID()) {
                Button button1 = new Button();
                button1.setPrefSize(460, 75);
                button1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
                vbxTicketTypes.getChildren().add(button1);
                AnchorPane anchorPaneUser1 = new AnchorPane();
                button1.setGraphic(anchorPaneUser1);
                Label labelName1 = new Label(ticketType.getTicketDescription());
                labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
                anchorPaneUser1.getChildren().add(labelName1);
                AnchorPane.setTopAnchor(labelName1, 10.0);
                AnchorPane.setLeftAnchor(labelName1, 68.0);
                ImageView imageViewUser1 = new ImageView();
                imageViewUser1.setFitHeight(50.0);
                imageViewUser1.setFitWidth(50.0);
                anchorPaneUser1.getChildren().add(imageViewUser1);
                imageViewUser1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/festival.png"))));
                AnchorPane.setTopAnchor(imageViewUser1, 8.0);
                AnchorPane.setLeftAnchor(imageViewUser1, 6.0);
                Label labelEmail1 = new Label(ticketType.getTicketPrice() + " DKK");
                anchorPaneUser1.getChildren().add(labelEmail1);
                AnchorPane.setTopAnchor(labelEmail1, 30.0);
                AnchorPane.setLeftAnchor(labelEmail1, 68.0);
            }

        }
    }


}
