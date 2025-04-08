/**
 * Validates user authentication and authorization in the ticket system.
 * Provides methods for credential verification and user role checking
 * by interfacing with the data access layer.
 */
package dk.easv.ticket_system.BLL;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.DAL.UserDAO;

import dk.easv.ticket_system.Models.UserModel;

import java.io.IOException;

public class LoginValidator {
    private UserDAO loginDao;        // Data access object for user authentication
    private UserModel userModel;     // Model for user data (unused)
    private String role;             // User role (unused)

    /**
     * Constructs a LoginValidator with a new UserDAO instance.
     *
     * @throws IOException If there's an error initializing the data access layer
     */
    public LoginValidator() throws IOException {
        this.loginDao = new UserDAO();
    }

    /**
     * Validates user login credentials.
     * Checks that email and password are not null or empty,
     * then verifies them against stored credentials.
     *
     * @param email The user's email address
     * @param password The user's password
     * @return true if credentials are valid, false otherwise
     */
    public boolean validateLogin(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }
        return loginDao.checkUserCredentials(email,password);
    }

    /**
     * Checks if a user has admin role.
     *
     * @param email The user's email address
     * @return true if the user has admin role, false otherwise
     */
    public boolean isAdmin(String email) {
        String role = loginDao.getRole(email);
        return "Admin".equals(role);
    }

    /**
     * Checks if a user has event coordinator role.
     *
     * @param email The user's email address
     * @return true if the user has event coordinator role, false otherwise
     */
    public boolean isEventCoordinator(String email) {
        String role = loginDao.getRole(email);
        return "Eventcoordinator".equals(role);
    }
}