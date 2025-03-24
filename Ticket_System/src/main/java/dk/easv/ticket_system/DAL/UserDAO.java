package dk.easv.ticket_system.DAL;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.easv.ticket_system.BE.User;

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
            String query = "SELECT passwordHash FROM TrueUsers WHERE email = ?";

            try (Connection conn = dbConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("passwordHash");
                    return BCrypt.verifyer().verify(password.toCharArray(), storedPassword).verified;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }



    public User createUser(User newUser) throws Exception {
        // SQL for inserting into Users table
        String userQuery = "INSERT INTO TrueUsers (email, passwordHash, roleID) VALUES (?, ?, ?)";
        // SQL for inserting into UserDetails table
        String detailsQuery = "INSERT INTO UserDetails (userID, firstName, lastName, phoneNumber) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             // Prepare the statement for inserting into Users table
             PreparedStatement userStmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Insert into Users table
            userStmt.setString(1, newUser.getEmail());
            userStmt.setString(2, newUser.getPassword()); // Assume it's already hashed
            userStmt.setInt(3, newUser.getRoleID());
            int affectedRows = userStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("User creation failed, no rows affected.");
            }

            // Get the generated userID
            int generatedUserID;
            try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedUserID = generatedKeys.getInt(1); // Retrieve the auto-generated userID
                } else {
                    throw new Exception("User creation failed, no userID returned.");
                }
            }

            // Insert into UserDetails table using the generated userID
            try (PreparedStatement detailsStmt = conn.prepareStatement(detailsQuery)) {
                detailsStmt.setInt(1, generatedUserID); // Set the generated userID
                detailsStmt.setString(2, newUser.getFirstName());
                detailsStmt.setString(3, newUser.getLastName());
                detailsStmt.setString(4, newUser.getPhoneNumber());
                detailsStmt.executeUpdate();
            }

            // Return a new User object with the generated userID and other details
            return new User(generatedUserID, newUser.getEmail(), newUser.getPassword(), newUser.getRoleID(),
                    newUser.getFirstName(), newUser.getLastName(), newUser.getPhoneNumber());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldn't create new user.");
        }
    }




    public List<User> getAllUsers() throws Exception {
        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT userID, email, passwordHash, roleID FROM TrueUsers ";

        try (Connection conn = dbConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("userID");
                String email = rs.getString("email");
                String password = rs.getString("passwordHash");
                int role = rs.getInt("roleID");

                User user = new User(id, email, password, role);
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
        String query = "SELECT role FROM users WHERE email = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

