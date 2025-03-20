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
        String query = "SELECT password FROM users WHERE email = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return BCrypt.verifyer().verify(password.toCharArray(), storedPassword).verified;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User createUser(User newUser) throws Exception {
        String query = "INSERT into dbo.Users (email, password, role) VALUES (?,?,?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, newUser.getEmail());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getRole());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Couldnt create new user");

        }
        return null;


    }

    public List<User>getAllUsers() throws Exception {

        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             Statement statement = conn.createStatement()) {
            String sql = "select * from dbo.users";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");

                User user = new User(id,email,password);
                users.add(user);
            }
            return users;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Something happened, cannot create new user.");
        }
    }

    @Override
    public void deleteUser(User userToDelete) throws Exception {

        //create a string with the sql statement to delete a given user from the database
        String sql = "DELETE FROM dbo.users WHERE id = ?;";

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

}

