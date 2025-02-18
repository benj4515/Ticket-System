package dk.easv.ticket_system;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class UserController {
    @FXML
    private TilePane tilePane;

    @FXML
    public void initialize() {
        Pane customPane1 = new Pane();
        customPane1.setPrefSize(550, 550);
        tilePane.getChildren().add(customPane1);
        customPane1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");

        Pane customPane2 = new Pane();
        customPane2.setPrefSize(550, 550);
        tilePane.getChildren().add(customPane2);
        customPane2.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");

        Pane customPane3 = new Pane();
        customPane3.setPrefSize(550, 550);
        tilePane.getChildren().add(customPane3);
        customPane3.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
    }
}
