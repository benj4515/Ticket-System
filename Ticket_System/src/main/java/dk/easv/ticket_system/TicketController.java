package dk.easv.ticket_system;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class TicketController {

    @FXML
    public FlowPane flowPane;
    public FlowPane flowPane2;

    @FXML
    public void initialize() {
        pane1();
    }

    public void pane1(){
        Pane customPane1 = new Pane();
        customPane1.setPrefSize(360, 150);
        flowPane.getChildren().add(customPane1);
        customPane1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(16, 16, 0, 16));
        customPane1.getChildren().add(vbox1);
        Label label1 = new Label("One free beer");
        label1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 0 0 12 0;");
        vbox1.getChildren().add(label1);
        Label label2 = new Label("🌐 Anywhere");
        label2.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #4B5563;");
        vbox1.getChildren().add(label2);
        Separator separator1 = new Separator();
        vbox1.getChildren().add(separator1);
        Label label3 = new Label("🎫 From 15.00 KR.");
        label3.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #4F46E5;");
        vbox1.getChildren().add(label3);
    }
}
