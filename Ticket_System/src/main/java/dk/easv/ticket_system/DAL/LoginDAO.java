package dk.easv.ticket_system.DAL;

import java.io.IOException;
import java.sql.*;


public class LoginDAO {
    private DBConnector dbConnector = new DBConnector();

    public LoginDAO() throws IOException {
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
                    return storedPassword.equals(password); // vi skal have krypteret
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }
}



