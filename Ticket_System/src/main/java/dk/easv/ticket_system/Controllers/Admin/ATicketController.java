/**
 * Controller for the admin ticket interface in the ticket system.
 * Manages the display and layout of ticket offerings in a flow layout.
 * Creates visually styled ticket panels with product information, location,
 * and pricing details using JavaFX components.
 */
package dk.easv.ticket_system.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ATicketController {

    @FXML
    public FlowPane flowPane;     // Primary flow pane for displaying ticket offerings
    public FlowPane flowPane2;    // Secondary flow pane for additional ticket displays

    /**
     * Initializes the controller and sets up the initial ticket display.
     * Called automatically by JavaFX when the FXML is loaded.
     */
    @FXML
    public void initialize() {
        pane1();
    }

    /**
     * Creates and configures a styled panel displaying a "One free beer" ticket.
     * Constructs a custom pane with title, location information, separator,
     * and pricing details with appropriate styling.
     */
    public void pane1(){
        Pane customPane1 = new Pane();                          // Container for the ticket display
        customPane1.setPrefSize(360, 150);                      // Set dimensions for the ticket panel
        flowPane.getChildren().add(customPane1);                // Add to the flow layout
        customPane1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");  // Apply styling with shadow effect

        VBox vbox1 = new VBox();                                // Vertical layout for ticket content
        vbox1.setPadding(new Insets(16, 16, 0, 16));            // Add padding around content
        customPane1.getChildren().add(vbox1);                   // Add to the ticket pane

        Label label1 = new Label("One free beer");              // Ticket title label
        label1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 0 0 12 0;");
        vbox1.getChildren().add(label1);

        Label label2 = new Label("🌐 Anywhere");                // Location information with icon
        label2.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #4B5563;");
        vbox1.getChildren().add(label2);

        Separator separator1 = new Separator();                 // Visual divider between sections
        vbox1.getChildren().add(separator1);

        Label label3 = new Label("🎫 From 15.00 KR.");          // Price information with ticket icon
        label3.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #4F46E5;");
        vbox1.getChildren().add(label3);
    }
}