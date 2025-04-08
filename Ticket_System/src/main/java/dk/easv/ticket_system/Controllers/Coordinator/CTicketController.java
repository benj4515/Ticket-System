/**
 * Controller for the ticket display interface in the ticket system.
 * Manages the visual presentation of tickets in a grid layout using flow panes.
 * Creates styled ticket panels with information such as ticket title, location,
 * and price displayed in a visually appealing format with proper styling and spacing.
 */
package dk.easv.ticket_system.Controllers.Coordinator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CTicketController {

    @FXML
    public FlowPane flowPane;        // Primary flow pane for displaying ticket items
    public FlowPane flowPane2;       // Secondary flow pane (unused in current implementation)

    /**
     * Initializes the controller.
     * Called automatically after the FXML file has been loaded.
     * Populates the flow pane with ticket displays.
     */
    @FXML
    public void initialize() {
        pane1();
    }

    /**
     * Creates and displays a sample ticket panel.
     * Constructs a styled pane containing ticket information with proper formatting:
     * - Title ("One free beer")
     * - Location indicator with emoji ("🌐 Anywhere")
     * - Visual separator
     * - Price information with ticket emoji ("🎫 From 15.00 KR.")
     *
     * The panel has a white background with rounded corners and a subtle drop shadow
     * for visual depth.
     */
    public void pane1(){
        // Create container for the ticket panel
        Pane customPane1 = new Pane();
        customPane1.setPrefSize(360, 150);
        flowPane.getChildren().add(customPane1);
        customPane1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");

        // Create vertical layout for ticket content with padding
        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(16, 16, 0, 16));
        customPane1.getChildren().add(vbox1);

        // Ticket title
        Label label1 = new Label("One free beer");
        label1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 0 0 12 0;");
        vbox1.getChildren().add(label1);

        // Ticket location
        Label label2 = new Label("🌐 Anywhere");
        label2.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #4B5563;");
        vbox1.getChildren().add(label2);

        // Visual separator between location and price
        Separator separator1 = new Separator();
        vbox1.getChildren().add(separator1);

        // Ticket price
        Label label3 = new Label("🎫 From 15.00 KR.");
        label3.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #4F46E5;");
        vbox1.getChildren().add(label3);
    }
}