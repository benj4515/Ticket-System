/**
 * Model class for managing Users in the ticket system.
 * Acts as an intermediary between the UI components and the business logic layer.
 * Maintains an observable collection of users for UI binding and delegates
 * operations to the UserManager business logic class.
 */
package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.Util.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserModel {
    /**
     * Observable collection of User objects for UI binding.
     * Allows JavaFX UI components to automatically update when the collection changes.
     */
    private final ObservableList<User> observableUsers;

    /**
     * Business logic manager for user operations.
     * Handles data processing and persistence operations for users.
     */
    private final UserManager userManager;

    /**
     * Constructs a new UserModel.
     * Initializes the user manager and populates the observable user list.
     *
     * @throws Exception If there's an error retrieving users from the data source
     */
    public UserModel() throws Exception {
        this.userManager = new UserManager();
        observableUsers = FXCollections.observableArrayList();
        observableUsers.addAll(userManager.getAllUsers());
    }

    /**
     * Gets the observable list of users for UI binding.
     *
     * @return Observable list containing all users
     */
    public ObservableList<User> getObservableUsers() {
        return observableUsers;
    }

    /**
     * Creates a new user in the system.
     * Adds the created user to the observable collection.
     *
     * @param newUser The user object to be created
     * @throws Exception If the user creation fails
     */
    public void createUser(User newUser) throws Exception {
        User u = userManager.createUser(newUser);
        observableUsers.add(u);
    }

    /**
     * Deletes a user from the system.
     * Removes the user from both the data source and observable collection.
     *
     * @param userToBeDeleted The user to be deleted
     * @throws Exception If the user deletion fails
     */
    public void deleteUser(User userToBeDeleted) throws Exception {
        userManager.deleteUser(userToBeDeleted);
        observableUsers.remove(userToBeDeleted);
    }

    /**
     * Refreshes and returns all users from the data source.
     * Updates the observable collection with the latest users.
     *
     * @return List of all users
     * @throws Exception If users retrieval fails
     */
    public List<User> getAllUsers() throws Exception {
        List<User> users = userManager.getAllUsers();

        observableUsers.clear();
        observableUsers.addAll(users);
        return userManager.getAllUsers();
    }

    /**
     * Gets simplified user information for the first panel in user controller.
     * Retrieves a lightweight list of users with minimal details.
     *
     * @return List of User objects with minimal information
     * @throws Exception If the retrieval fails
     */
    public List<User> getAllUsersForUserCtlr() throws Exception {
        List<User> users = userManager.getAllUsersForUserCtlr();

        observableUsers.clear();
        observableUsers.addAll(users);
        return userManager.getAllUsersForUserCtlr();
    }

    /**
     * Gets detailed user information for the second panel in user controller.
     * Retrieves users with complete profile information.
     *
     * @return List of User objects with detailed information
     * @throws Exception If the retrieval fails
     */
    public List<User> getAllUserDetails() throws Exception {
        List<User> users = userManager.getAllUserDetails();

        observableUsers.clear();
        observableUsers.addAll(users);
        return users;
    }

    /**
     * Gets the first name from the current user context.
     *
     * @return First name of the user
     * @throws Exception If the retrieval fails
     */
    public String getFirstName() throws Exception {
        return userManager.getFirstName();
    }

    /**
     * Gets the last name from the current user context.
     *
     * @return Last name of the user
     * @throws Exception If the retrieval fails
     */
    public String getLastName() throws Exception {
        return userManager.getLastName();
    }

    /**
     * Gets the email address from the current user context.
     *
     * @return Email address of the user
     * @throws Exception If the retrieval fails
     */
    public String getEmail() throws Exception {
        return userManager.getEmail();
    }

    /**
     * Retrieves all user names from the system.
     * Returns a list of users with only name information populated.
     *
     * @return List of User objects containing only name information
     * @throws Exception If the retrieval fails
     */
    public List<User> getAllUserNames() throws Exception {
        return userManager.getAllUserNames();
    }

    /**
     * Retrieves all user emails from the system.
     * Returns a list of users with only email information populated.
     *
     * @return List of User objects containing only email information
     * @throws Exception If the retrieval fails
     */
    public List<User> getAllUserEmails() throws Exception {
        return userManager.getAllUserEmails();
    }
}