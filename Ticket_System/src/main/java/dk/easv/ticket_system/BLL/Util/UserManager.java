/**
 * Manages user-related business logic in the ticket system.
 * Provides methods for creating, retrieving, updating, and deleting users,
 * as well as accessing specific user properties through a data access layer.
 */
package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.DAL.UserDAO;
import dk.easv.ticket_system.DAL.IUserDataAccess;

import java.io.IOException;
import java.util.List;

public class UserManager {

    private final IUserDataAccess dataAccess;    // Data access object for user operations

    /**
     * Constructs a UserManager with a new UserDAO instance.
     *
     * @throws IOException If there's an error initializing the data access layer
     */
    public UserManager() throws IOException {
        dataAccess = new UserDAO();
    }

    /**
     * Creates a new user in the system.
     *
     * @param newUser The user object to create
     * @return The created user with assigned ID
     * @throws Exception If there's an error during user creation
     */
    public User createUser(User newUser) throws Exception {
        return dataAccess.createUser(newUser);
    }

    public void updateUser(User user) throws Exception {
         dataAccess.updateUser(user);
    }

    /**
     * Deletes an existing user.
     *
     * @param userToDelete The user to be deleted
     * @throws Exception If there's an error during user deletion
     */
    public void deleteUser(User userToDelete) throws Exception {
        dataAccess.deleteUser(userToDelete);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return A list of all users
     * @throws Exception If there's an error retrieving users
     */
    public List<User> getAllUsers() throws Exception {
        return dataAccess.getAllUsers();
    }

    /**
     * Retrieves all users specifically for the user controller.
     *
     * @return A list of users formatted for the user controller
     * @throws Exception If there's an error retrieving users
     */
    public List<User> getAllUsersForUserCtlr() throws Exception {
        return dataAccess.getAllUsersForUserCtlr();
    }

    /**
     * Retrieves detailed information for all users.
     *
     * @return A list of users with complete details
     * @throws Exception If there's an error retrieving user details
     */
    public List<User> getAllUserDetails() throws Exception {
        return dataAccess.getAllUserDetails();
    }

    /**
     * Gets the role name of a user.
     *
     * @return The name of the user's role
     * @throws Exception If there's an error retrieving the role name
     */
    public String getRoleName() throws Exception {
        return  dataAccess.getUserRoleName();
    }

    /**
     * Gets the first name of a user.
     *
     * @return The user's first name
     * @throws Exception If there's an error retrieving the first name
     */
    public String getFirstName() throws Exception {
        return dataAccess.getFirstName();
    }

    /**
     * Gets the last name of a user.
     *
     * @return The user's last name
     * @throws Exception If there's an error retrieving the last name
     */
    public String getLastName() throws Exception {
        return dataAccess.getLastName();
    }

    /**
     * Gets the email address of a user.
     *
     * @return The user's email address
     * @throws Exception If there's an error retrieving the email
     */
    public String getEmail() throws Exception {
        return dataAccess.getEmail();
    }

    /**
     * Retrieves all email addresses of users in the system.
     *
     * @return A list of users with their email addresses
     * @throws Exception If there's an error retrieving user emails
     */
    public List <User> getAllUserEmails() throws Exception {
        return dataAccess.getAllUserEmails();
    }

    /**
     * Retrieves all user names in the system.
     *
     * @return A list of users with their names
     * @throws Exception If there's an error retrieving user names
     */
    public List <User> getAllUserNames() throws Exception {
        return dataAccess.getAllUserNames();
    }
}