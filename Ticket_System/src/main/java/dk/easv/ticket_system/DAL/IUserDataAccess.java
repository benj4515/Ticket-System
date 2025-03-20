package dk.easv.ticket_system.DAL;

import dk.easv.ticket_system.BE.User;

public interface IUserDataAccess {


    User createUser(User newUser) throws Exception;

    void deleteUser(User user) throws Exception;


}
