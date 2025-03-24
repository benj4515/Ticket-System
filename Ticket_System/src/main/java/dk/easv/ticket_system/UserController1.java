package dk.easv.ticket_system;

import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.Models.UserModel;

public class UserController1 {

    private UserModel userModel;


    public UserController1() {
        try {
            userModel = new UserModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

    }
}
