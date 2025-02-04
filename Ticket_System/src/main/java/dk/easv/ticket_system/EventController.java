package dk.easv.ticket_system;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EventController {


    @FXML
    private BorderPane bpnTopBar;
    @FXML
    private BorderPane bpnFullPane;

    private boolean listenersAdded = false;

    private double width;


    public void initialize() {
        // TODO: Something


        //Forces resizing of the window because scenebuilder is fucking with me
        if (!listenersAdded) {
            Stage primaryStage = (Stage) Window.getWindows().get(0);
            width = primaryStage.getWidth();
            bpnFullPane.setPrefWidth(width - 256);

            primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
                width = newVal.doubleValue();
                bpnFullPane.setPrefWidth(width - 256);
                System.out.println("Width: " + width);
            });

            listenersAdded = true;
        }
    }
}
