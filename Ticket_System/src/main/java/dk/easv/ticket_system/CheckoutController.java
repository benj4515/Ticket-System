package dk.easv.ticket_system;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.Objects;

public class CheckoutController {


    @FXML
    private FlowPane flowPane;

    public void initialize(){
        System.out.println("cumtown");
        image1();
        image2();
        image3();
    }

    public void image1(){
        ImageView imageView1 = new ImageView();
        imageView1.isPreserveRatio();
        imageView1.setFitWidth(1150);
        imageView1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Checkout-Custommer.png"))));
        flowPane.getChildren().add(imageView1);
    }

    public void image2(){
        ImageView imageView2 = new ImageView();
        imageView2.isPreserveRatio();
        imageView2.setFitWidth(1150);
        imageView2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Checkout-Showing.png"))));
        flowPane.getChildren().add(imageView2);
    }

    public void image3(){
        ImageView imageView3 = new ImageView();
        imageView3.isPreserveRatio();
        imageView3.setFitWidth(1150);
        imageView3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Checkout-Ticket.png"))));
        flowPane.getChildren().add(imageView3);
    }
}
