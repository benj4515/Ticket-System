package dk.easv.ticket_system;


import dk.easv.ticket_system.Controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;



public class HelloApplication extends Application {
    private final LoginController loginController = new LoginController();


    public static void main(String[] args) throws Exception {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        loginController.Start();


    }

}

