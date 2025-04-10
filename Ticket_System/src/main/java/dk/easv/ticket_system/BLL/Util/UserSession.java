/**
 * Manages the user session state in the ticket system.
 * Provides a centralized way to track the currently logged-in user
 * using a singleton-like pattern with static methods.
 */
package dk.easv.ticket_system.BLL.Util;

import dk.easv.ticket_system.BE.User;

public class UserSession {
    private static User loggedInUser;    // Stores the reference to the currently logged-in user

    /**
     * Sets the currently logged-in user.
     *
     * @param user The user who has successfully logged in
     */
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The current user, or null if no user is logged in
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }

}