package dk.easv.ticket_system.BLL;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.DAL.UserDAO;

import dk.easv.ticket_system.Models.UserModel;

import java.io.IOException;

public class LoginValidator {
    private UserDAO loginDao;
    private UserModel userModel;
    private String role;





    public LoginValidator() throws IOException {
        this.loginDao = new UserDAO();

    }

    public boolean validateLogin(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }
        return loginDao.checkUserCredentials(email,password);
    }


    public boolean isAdmin(String email) {
        String role = loginDao.getRole(email);
        return "Admin".equals(role);
    }


    public boolean isEventCoordinator(String email) {
        String role = loginDao.getRole(email);
        return "Eventcoordinator".equals(role);
    }


}
