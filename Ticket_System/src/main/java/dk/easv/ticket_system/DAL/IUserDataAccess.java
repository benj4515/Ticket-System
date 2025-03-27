package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.User;

import java.util.List;

public interface IUserDataAccess {

    /*
    private int id;
    private String email;
    private String password;
    private int role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    */

    User createUser(User newUser) throws Exception;

    void deleteUser(User user) throws Exception;

    List<User> getAllUsers() throws Exception;

    String getRole(String role) throws Exception;


}
