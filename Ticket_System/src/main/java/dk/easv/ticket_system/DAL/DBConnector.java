/**
 * Database connector for the ticket system.
 * Implements the singleton pattern to provide a centralized connection point to the SQL Server database.
 * Reads database configuration from an external properties file to securely store connection settings
 * separate from code. Provides methods for establishing and managing database connections.
 */
package dk.easv.ticket_system.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBConnector {

    /**
     * Path to the configuration file containing database connection properties.
     * The file should contain key-value pairs for Server, Database, User, and Password.
     */
    private static final String PROP_FILE = "Ticket_System/config/config.settings";

    /**
     * Singleton instance of the DBConnector.
     * Ensures only one instance is created throughout the application lifecycle.
     */
    private static DBConnector instance;

    /**
     * SQL Server data source configured with connection properties.
     * Provides the connection pool and handles connection details.
     */
    private final SQLServerDataSource dataSource;

    /**
     * Private constructor that initializes the data source with connection parameters.
     * Loads connection properties from the configuration file and configures
     * the SQL Server data source with appropriate connection settings.
     *
     * @throws IOException If the configuration file cannot be read or is missing
     */
    public DBConnector() throws IOException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream(PROP_FILE));

        dataSource = new SQLServerDataSource();
        dataSource.setServerName(databaseProperties.getProperty("Server"));
        dataSource.setDatabaseName(databaseProperties.getProperty("Database"));
        dataSource.setUser(databaseProperties.getProperty("User"));
        dataSource.setPassword(databaseProperties.getProperty("Password"));
        dataSource.setPortNumber(1433);
        dataSource.setTrustServerCertificate(true);
    }

    /**
     * Gets the singleton instance of the DBConnector.
     * Creates a new instance if one does not already exist.
     *
     * @return The singleton DBConnector instance
     * @throws IOException If there's an error initializing the connector
     */
    public static DBConnector getInstance() throws IOException {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    /**
     * Establishes and returns a connection to the database.
     * Each call creates a new connection that should be closed after use.
     *
     * @return A new Connection object to the configured database
     * @throws SQLServerException If the connection cannot be established
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    /**
     * Tests database connectivity.
     * Attempts to establish a connection and outputs success or failure message.
     * Uses try-with-resources to ensure the connection is closed properly.
     */
    public static void checkConnection() {
        try (Connection connection = getInstance().getConnection()) {
            System.out.println("Connection successful: " + !connection.isClosed());
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}