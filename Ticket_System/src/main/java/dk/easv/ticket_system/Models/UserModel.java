package dk.easv.ticket_system.Models;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.Util.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserModel {
    private final ObservableList<User> observableUsers;
    private final UserManager userManager;

    public UserModel() throws Exception {
        this.userManager = new UserManager();
        observableUsers = FXCollections.observableArrayList();
        observableUsers.addAll(userManager.getAllUsers());
    }

    public ObservableList<User> getObservableUsers() {
        return observableUsers;
    }

    public void createUser(User newUser) throws Exception {
        User u = userManager.createUser(newUser);
        observableUsers.add(u);
    }

    public void deleteUser(User userToBeDeleted) throws Exception {
        userManager.deleteUser(userToBeDeleted);
        observableUsers.remove(userToBeDeleted);
    }

    public List<User> getAllUsers() throws Exception {
        return userManager.getAllUsers();
    }

    // brug mig til at populate panel 1 i usercontroller
    public List<User> getAllUsersForUserCtlr() throws Exception {
        return userManager.getAllUsersForUserCtlr();
    }

    //brug mig til at populate panel 2 i usercontroller
    public List<User> getAllUserDetails() throws Exception {
        return userManager.getAllUserDetails();
    }

}
