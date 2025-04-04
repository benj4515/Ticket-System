package dk.easv.ticket_system.DAL;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.Util.UserSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO implements IUserDataAccess {
    private DBConnector dbConnector = new DBConnector();

    public UserDAO() throws IOException {
        this.dbConnector = new DBConnector();
    }

    public boolean checkUserCredentials(String email, String password) {
            String query = "SELECT u.userID, u.email, r.roleName, u.passwordHash " +
                    "FROM TrueUsers u " +
                    "JOIN roles r ON u.roleID = r.roleID " +
                    "WHERE u.email = ?";

            try (Connection conn = dbConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("passwordHash");
                    if (BCrypt.verifyer().verify(password.toCharArray(), storedPassword).verified) {
                        int id = rs.getInt("userID");
                        String roleName = rs.getString("roleName");

                        User loggedInUser = new User(id, email, roleName);
                        UserSession.setLoggedInUser(loggedInUser);


                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

//ticket name, description, price

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

    @Override
    public void deleteUser(User userToDelete) throws Exception {

        //create a string with the sql statement to delete a given user from the database
        String sql = "DELETE FROM dbo.TrueUsers WHERE id = ?;";

        //try with resources to connect to the database and execute the delete statement
        try (Connection conn = dbConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, userToDelete.getUserId());

            stmt.executeUpdate();

            //throw an exception if it fails
        } catch (SQLException e) {
            throw new Exception("Couldn't delete User from database", e);
        }
    }

    public String getRole(String email) {
        String query = "SELECT r.roleName FROM Roles r " +
                "JOIN TrueUsers u ON r.roleID = u.roleID " +
                "WHERE u.email = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("roleName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
}

