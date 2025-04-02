package dk.easv.ticket_system;


import dk.easv.ticket_system.BLL.Util.EmailHandler;
import dk.easv.ticket_system.BLL.Util.TicketManager;
import dk.easv.ticket_system.Controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class HelloApplication extends Application {
    private final LoginController loginController = new LoginController();


    public static void main(String[] args) throws Exception {
        new EmailHandler().send("A new message", """
      Dear reader,
     
      Hello world
      Best regards,
      EventHub
       """);
        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
        loginController.Start();


    }

}

