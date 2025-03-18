module dk.easv.ticket_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;


    opens dk.easv.ticket_system to javafx.fxml;
    exports dk.easv.ticket_system;

}