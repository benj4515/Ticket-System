package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.User;

import java.util.List;

public interface IUserDataAccess {


    User createUser(User newUser) throws Exception;

    void deleteUser(User user) throws Exception;

    List<User> getAllUsers() throws Exception;
}
