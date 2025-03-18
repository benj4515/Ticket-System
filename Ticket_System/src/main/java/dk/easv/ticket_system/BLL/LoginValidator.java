package dk.easv.ticket_system.BLL;

import dk.easv.ticket_system.DAL.DBConnector;
import dk.easv.ticket_system.DAL.LoginDAO;

import java.io.IOException;

public class LoginValidator {
    private LoginDAO loginDao;

    public LoginValidator() throws IOException {
        this.loginDao = new LoginDAO();

    }

    public boolean validateLogin(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }
        return loginDao.checkUserCredentials(email,password);
    }





}
