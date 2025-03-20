package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.DAL.UserDAO;
import dk.easv.ticket_system.DAL.IUserDataAccess;

import java.io.IOException;

public class UserManager {

    //private instance variables
    private final IUserDataAccess dataAccess;

    public UserManager() throws IOException {
    dataAccess = new UserDAO();
    }

    public User createUser(User newUser) throws Exception {
    return dataAccess.createUser(newUser);
    }

    public void deleteUser(User userToDelete) throws Exception {
    dataAccess.deleteUser(userToDelete);
    }

}
