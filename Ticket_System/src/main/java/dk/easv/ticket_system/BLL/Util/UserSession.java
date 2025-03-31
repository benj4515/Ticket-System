package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.User;

public class UserSession {
    private static User loggedInUser;


    public static void setLoggedInUser (User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser () {
        return loggedInUser;
    }

    public static void logout () {
        loggedInUser = null;
    }
}
