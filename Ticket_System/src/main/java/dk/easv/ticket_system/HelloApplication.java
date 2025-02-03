package dk.easv.ticket_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    private LoginController loginController = new LoginController();




    @Override
    public void start(Stage stage) throws IOException {
        loginController.Start();
    }

    public static void main(String[] args) {
        launch();

    }
}