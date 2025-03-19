package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.DAL.UserDAO;
import dk.easv.ticket_system.BLL.Util.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
    private final ObservableList<User> observableUsers;
    private final UserManager userManager;
    private final UserDAO userDAO;

    public UserModel() throws Exception {
        userDAO = new UserDAO();
        this.userManager = new UserManager();
        observableUsers = FXCollections.observableArrayList();
        observableUsers.addAll(userDAO.getAllUsers());
    }

    public ObservableList<User> getObservableUsers() {return observableUsers;}

    public void createUser(User newUser) throws Exception {
        User u = null;
        try {
            u = userManager.createUser(newUser);
            observableUsers.add(u);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteUser(User userToBeDeleted) throws Exception {

        userManager.deleteUser(userToBeDeleted);
        observableUsers.remove(userToBeDeleted);
    }
}
