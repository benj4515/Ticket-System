package dk.easv.ticket_system.Controllers.Coordinator;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.Objects;

public class CCheckoutController {


    public ImageView imvTicketPreview;
    @FXML
    private FlowPane flowPane;

    public void initialize(){
        imvTicketPreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/TicketPreview.png"))));
        System.out.println("cumtown");
    }

}
