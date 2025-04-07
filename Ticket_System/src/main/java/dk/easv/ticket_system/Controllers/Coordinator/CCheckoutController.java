package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.TicketType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class CCheckoutController {


    public ImageView imvTicketPreview;
    public VBox vbxTicketTypes;
    @FXML
    private FlowPane flowPane;

    private CEventDetailsController cEventDetailsController;

    public CCheckoutController() {

    }

    public void initialize(){
        imvTicketPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/RealTicketPreview.png"))));
    }

    public void setSelectedTickets(ObservableList<TicketType> selectedTickets) {
        System.out.println(selectedTickets + " selectedTickets");
        for (TicketType ticketType : selectedTickets) {
            Pane pane = new Pane();
            pane.setPrefSize(1000, 50);
            vbxTicketTypes.getChildren().add(pane);
            AnchorPane anchorPaneUser1 = new AnchorPane();
            Label labelName1 = new Label(ticketType.getTicketDescription());
            labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
            anchorPaneUser1.getChildren().add(labelName1);
            AnchorPane.setTopAnchor(labelName1, 10.0);
            AnchorPane.setLeftAnchor(labelName1, 68.0);
            Label labelEmail1 = new Label(ticketType.getTicketPrice() + " DKK");
            anchorPaneUser1.getChildren().add(labelEmail1);
            AnchorPane.setTopAnchor(labelEmail1, 30.0);
            AnchorPane.setLeftAnchor(labelEmail1, 68.0);
            pane.getChildren().add(anchorPaneUser1);
        }
    }

    public void HandleOneFreeBeer(ActionEvent actionEvent) {
    }

    public void Handle50OffOneDrink(ActionEvent actionEvent) {

    }

    public void Handle1SetOfFreeEarplugs(ActionEvent actionEvent) {
    }
}
