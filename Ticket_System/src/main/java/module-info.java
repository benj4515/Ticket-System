module dk.easv.ticket_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.easv.ticket_system to javafx.fxml;
    exports dk.easv.ticket_system;
}