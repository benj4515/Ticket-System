package dk.easv.ticket_system.BLL;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.easv.ticket_system.DAL.DBConnector;
import dk.easv.ticket_system.DAL.LoginDAO;

import java.io.IOException;

public class LoginValidator {
    private LoginDAO loginDao;
    //private String bcryptHashString;

    public LoginValidator() throws IOException {
        this.loginDao = new LoginDAO();

    }

    public boolean validateLogin(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }
        return loginDao.checkUserCredentials(email,password);
    }


    public boolean isAdmin(String email) {
        String role = loginDao.getRole(email);
        return "admin".equals(role);
    }


    public boolean isEventCoordinator(String email) {
        String role = loginDao.getRole(email);
        return "eventcoordinator".equals(role);
    }
}
