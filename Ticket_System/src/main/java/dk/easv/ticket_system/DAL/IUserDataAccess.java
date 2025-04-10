package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.User;

import java.util.List;

public interface IUserDataAccess {



    User createUser(User newUser) throws Exception;

    void deleteUser(User user) throws Exception;

    List<User> getAllUsers() throws Exception;

    String getRole(String role) throws Exception;

    List<User> getAllUsersForUserCtlr() throws Exception;

    List<User> getAllUserDetails() throws Exception;

    public String getUserRoleName() throws Exception;

    public String getFirstName() throws Exception;

    public String getLastName() throws Exception;

    public String getEmail() throws Exception;

    List <User> getAllUserEmails() throws Exception;

    List <User> getAllUserNames() throws Exception;

    void updateUser(User user) throws Exception;




}
