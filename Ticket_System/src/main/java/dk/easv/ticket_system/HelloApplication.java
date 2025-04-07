package dk.easv.ticket_system;


import dk.easv.ticket_system.BLL.Util.TicketManager;
import dk.easv.ticket_system.Controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;


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

