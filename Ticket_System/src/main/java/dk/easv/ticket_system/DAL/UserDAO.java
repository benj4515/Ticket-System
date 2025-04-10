/**
 * Data Access Object (DAO) for User entities in the ticket system.
 * Provides methods to interact with user-related tables in the database.
 * Handles CRUD operations and authentication for users, including creating,
 * retrieving, and deleting user records. Implements the IUserDataAccess interface
 * to provide standardized access to user data throughout the application.
 */
package dk.easv.ticket_system.DAL;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.Util.UserSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO implements IUserDataAccess {
    /**
     * Database connector instance for establishing connections to the database.
     */
    private DBConnector dbConnector = new DBConnector();

    /**
     * Constructs a new UserDAO with a fresh database connector.
     *
     * @throws IOException If there's an error initializing the DB connector
     */
    public UserDAO() throws IOException {
        this.dbConnector = new DBConnector();
    }

    /**
     * Validates user login credentials against the database.
     * Checks if the provided email exists and if the password hash matches.
     * On successful authentication, creates a UserSession with the logged-in user's information.
     *
     * @param email The email address entered by the user
     * @param password The password entered by the user
     * @return true if credentials are valid, false otherwise
     */
    public boolean checkUserCredentials(String email, String password) {
        String query = "SELECT u.userID, u.email, u.passwordHash, r.roleID, r.roleName, " +
                "ud.firstName, ud.lastName, ud.phoneNumber " +
                "FROM TrueUsers u " +
                "JOIN roles r ON u.roleID = r.roleID " +
                "LEFT JOIN UserDetails ud ON u.userID = ud.userID " +
                "WHERE u.email = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();



            if (rs.next()) {
                String storedPassword = rs.getString("passwordHash");
                if (BCrypt.verifyer().verify(password.toCharArray(), storedPassword).verified) {
                    int userID = rs.getInt("userID");
                    int roleID = rs.getInt("roleID");
                    String roleName = rs.getString("roleName");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String phoneNumber = rs.getString("phoneNumber");

                    // Use the complete constructor that actually sets the fields
                    User loggedInUser = new User(userID, email, storedPassword, roleID, roleName,
                            firstName, lastName, phoneNumber);
                    UserSession.setLoggedInUser(loggedInUser);

                    System.out.println("User logged in successfully: " + loggedInUser);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//ticket name, description, price

    /**
     * Creates a new user in the database.
     * Inserts data into both TrueUsers and UserDetails tables in a single transaction.
     * Generates a unique user ID and associates it with the user's details.
     *
     * @param newUser The user object containing details to be inserted
     * @return The created user object with its generated ID
     * @throws Exception If database operations fail or constraints are violated
     */
    public User createUser(User newUser) throws Exception {

        // 3 SQL statements, one for each implicated table.
        String userQuery = "INSERT INTO TrueUsers (email, passwordHash, roleID) VALUES (?, ?, ?)";

        String detailsQuery = "INSERT INTO UserDetails (userID, firstName, lastName, phoneNumber) VALUES (?, ?, ?, ?)";

        //Unused statement, used this when user constructor relied on rolename
        //String roleQuery = "SELECT roleName FROM Roles WHERE roleID = ?";


        try (Connection conn = dbConnector.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Insert into TrueUsers table
            userStmt.setString(1, newUser.getEmail());
            userStmt.setString(2, newUser.getPassword());
            userStmt.setInt(3, newUser.getRoleID());
            int affectedRows = userStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("User creation failed, no rows affected.");
            }

            //We store the generated userID, so we can use it in our UserDetails insert.
            int generatedUserID;
            try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedUserID = generatedKeys.getInt(1);
                } else {
                    throw new Exception("User creation failed, no userID returned.");
                }
            }

            // Insert into UserDetails table using the generated userID
            try (PreparedStatement detailsStmt = conn.prepareStatement(detailsQuery)) {
                detailsStmt.setInt(1, generatedUserID);
                detailsStmt.setString(2, newUser.getFirstName());
                detailsStmt.setString(3, newUser.getLastName());
                detailsStmt.setString(4, newUser.getPhoneNumber());
                detailsStmt.executeUpdate();
            }




            return new User(generatedUserID, newUser.getEmail(), newUser.getPassword(), newUser.getRoleID(), // Updated to roleName
                    newUser.getFirstName(), newUser.getLastName(), newUser.getPhoneNumber());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new user.");
        }
    }
    // userID, email, passwordHash, roleName, firstName, lastName, phoneNumber


    /**
     * Retrieves all users from the database with complete details.
     * Joins the TrueUsers, Roles, and UserDetails tables to fetch comprehensive user information.
     *
     * @return List of all User objects with complete details
     * @throws Exception If the database query fails
     */
    public List<User> getAllUsers() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT u.userID, u.email, u.passwordHash, u.roleID, r.roleName, d.firstName, d.lastName, d.phoneNumber " +
                "FROM TrueUsers u " +
                "JOIN Roles r ON u.roleID = r.roleID " +
                "JOIN UserDetails d ON u.userID = d.userID";

        try (Connection conn = dbConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("userID");
                String email = rs.getString("email");
                String password = rs.getString("passwordHash");
                int roleID = rs.getInt("roleID");
                String roleName = rs.getString("roleName");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phoneNumber");

                User user = new User(id, email, password, roleID, roleName, firstName, lastName, phoneNumber);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot retrieve users.");
        }
    }

    /**
     * Deletes a user from the database.
     * Permanently removes the user record based on its ID.
     * Note: The SQL query uses "id" column which may differ from "userID" in the schema.
     *
     * @param userToDelete The user object to be deleted
     * @throws Exception If the deletion operation fails
     */
    @Override
    public void deleteUser(User userToDelete) throws Exception {

        //create a string with the sql statement to delete a given user from the database
        String sql = "DELETE FROM dbo.TrueUsers WHERE id = ?;";

        //try with resources to connect to the database and execute the delete statement
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, userToDelete.getUserID());

            stmt.executeUpdate();

            //throw an exception if it fails
        } catch (SQLException e) {
            throw new Exception("Couldn't delete User from database", e);
        }
    }

    /**
     * Gets a user's role by their email address.
     * Joins the Roles and TrueUsers tables to find the roleName for a specific email.
     *
     * @param email The email address of the user
     * @return The role name if found, null otherwise
     */
    public String getRole(String email) {
        String query = "SELECT r.roleName FROM TrueUsers u " +
                "JOIN roles r ON u.roleID = r.roleID " +
                "WHERE u.email = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String roleName = rs.getString("roleName");
                System.out.println("Role found: " + roleName);  // Debug output
                return roleName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets minimal user information for the user controller.
     * Retrieves only email, first name and last name from the database.
     * Used for lightweight user list displays.
     *
     * @return List of User objects with minimal details
     * @throws Exception If the database query fails
     */
    @Override
    public List<User> getAllUsersForUserCtlr() throws Exception {
        List<User> users = new ArrayList<>();
        String query = "SELECT tu.email, ud.firstName, ud.lastName " +
                "FROM TrueUsers tu " +
                "JOIN UserDetails ud ON tu.userID = ud.userID";;

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                User user = new User(email, firstName, lastName);
                users.add(user);
            }
        }

        return users;
    }

    /**
     * Gets user details including role information.
     * Joins the TrueUsers, UserDetails, and Roles tables to fetch
     * email, first name, last name, and role name for all users.
     *
     * @return List of User objects with names, email and role
     * @throws Exception If the database query fails
     */
    @Override
    public List<User> getAllUserDetails() throws Exception {
        List<User> users = new ArrayList<>();
        String query = "SELECT tu.email, ud.firstName, ud.lastName, r.roleName " +
                "FROM TrueUsers tu " +
                "JOIN UserDetails ud ON tu.userID = ud.userID " +
                "LEFT JOIN Roles r ON tu.roleID = r.roleID";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String roleName = rs.getString("roleName");

                User user = new User(email, firstName, lastName, roleName);
                users.add(user);
            }
        }

        return users;
    }

    /**
     * Gets a role name from the database.
     * Note: Currently returns only the last role name found since it doesn't filter results.
     *
     * @return The role name, or empty string if none found
     * @throws Exception If the database query fails
     */
    public String getUserRoleName() throws Exception {
        String role = "";
        String query = "SELECT roleName FROM Roles";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String roleName = rs.getString("roleName");
                role = roleName;
            }
        }
        return role;
    }

    /**
     * Gets a first name from the database.
     * Note: Currently returns only the last first name found since it doesn't filter results.
     *
     * @return The first name, or empty string if none found
     * @throws Exception If the database query fails
     */
    @Override
    public String getFirstName() throws Exception {
        String firstName = "";
        String query = "SELECT firstName FROM UserDetails";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String firstname = rs.getString("firstName");
                firstName = firstname;
            }
        }
        return firstName;
    }

    /**
     * Gets a last name from the database.
     * Note: Currently returns only the last last name found since it doesn't filter results.
     *
     * @return The last name, or empty string if none found
     * @throws Exception If the database query fails
     */
    @Override
    public String getLastName() throws Exception {
        String lastName = "";
        String query = "SELECT lastName FROM UserDetails";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String lastname = rs.getString("lastName");
                lastName = lastname;
            }
        }
        return lastName;
    }

    /**
     * Gets an email from the database.
     * Note: Currently returns only the last email found since it doesn't filter results.
     *
     * @return The email, or empty string if none found
     * @throws Exception If the database query fails
     */
    @Override
    public String getEmail() throws Exception {
        String eMail = "";
        String query = "SELECT email FROM TrueUsers";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String email = rs.getString("email");
                eMail = email;
            }
        }
        return eMail;
    }

    /**
     * Gets all user emails from the database.
     * Creates a list of minimal User objects with only email information.
     *
     * @return List of User objects containing only emails
     * @throws Exception If the database query fails
     */
    @Override
    public List<User> getAllUserEmails() throws Exception {
        List<User> users = new ArrayList<>();
        String query = "SELECT email FROM TrueUsers;";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String email = rs.getString("email");

                User user = new User(email);
                users.add(user);
            }
        }

        return users;
    }

    /**
     * Gets all user names from the database.
     * Creates a list of minimal User objects with only name information.
     * Note: The query selects "fullName" and "lastName" columns which might not match the schema.
     *
     * @return List of User objects containing only first and last names
     * @throws Exception If the database query fails
     */
    @Override
    public List<User> getAllUserNames() throws Exception {
        List<User> users = new ArrayList<>();
        String query = "SELECT fullName, lastName FROM UserDetails;";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String firstName = rs.getString("fullName");
                String lastName = rs.getString("lastName");


                User user = new User(firstName, lastName);
                users.add(user);
            }
        }

        return users;
    }

    @Override
    public void updateUser(User user) throws Exception {
        String sql = "UPDATE TrueUsers SET email = ?, passwordHash = ?, roleID = ? WHERE userID = ?";
        String sql1 = "UPDATE UserDetails SET firstName = ?, lastName = ?, phoneNumber = ? WHERE userID = ?";

        try (Connection conn = dbConnector.getConnection()){
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getEmail());
                stmt.setString(2, user.getPassword());
                stmt.setInt(3, user.getRoleID());
                stmt.setInt(4, user.getUserID());

                stmt.executeUpdate();
            } catch (SQLException e) {
                conn.rollback();
                throw new Exception("Couldn't update User in database", e);
            }

            try (PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
                stmt1.setString(1, user.getFirstName());
                stmt1.setString(2, user.getLastName());
                stmt1.setString(3, user.getPhoneNumber());
                stmt1.setInt(4, user.getUserID());

                stmt1.executeUpdate();
            } catch (SQLException e) {
                conn.rollback();
                throw new Exception("Couldn't update User in database", e);
            }

            conn.commit();

        }

    }
}