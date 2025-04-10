/**
 * Represents a user in the ticket system.
 * Contains user information such as identification, authentication details,
 * personal information, and role assignment.
 */
package dk.easv.ticket_system.BE;

public class User {
    private int id;                     // Internal ID used by some constructors (possibly legacy)
    private int userID;                 // Unique identifier for the user
    private String email;               // User's email address, also used for login
    private String password;            // User's password for authentication
    private int roleID;                 // User's role ID for authorization purposes
    private String firstName;           // User's first name
    private String lastName;            // User's last name
    private String phoneNumber;         // User's phone number
    private String roleName;            // Display name of the user's role



    /**
     * Constructs a complete user with all primary attributes.
     *
     * @param userID The user's unique identifier
     * @param email The user's email address
     * @param password The user's password
     * @param roleID The user's role ID
     * @param roleName The name of the user's role
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param phoneNumber The user's phone number
     */
    public User(int userID, String email, String password, int roleID, String roleName, String firstName, String lastName, String phoneNumber) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.roleName = roleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a user with authentication, role and name information.
     *
     * @param email The user's email address
     * @param password The user's password
     * @param role The user's role identifier
     * @param firstName The user's first name
     * @param lastName The user's last name
     */
    public User(String email, String password, int role, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleID = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }





    /**
     * Constructs a user with a generated ID and complete information except role name.
     * Note: This constructor has an empty implementation.
     *
     * @param generatedUserID A temporary ID for the user
     * @param email The user's email address
     * @param password The user's password
     * @param roleID The user's role ID
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param phoneNumber The user's phone number
     */
    public User(int generatedUserID, String email, String password,
                int roleID, String firstName, String lastName, String phoneNumber) {
    }



    /**
     * Constructs a user with only email and name.
     * Note: This constructor has an empty implementation.
     *
     * @param email The user's email address
     * @param firstName The user's first name
     * @param lastName The user's last name
     */
    public User(String email, String firstName, String lastName) {
    }

    /**
     * Constructs a user with email, name, and role name.
     *
     * @param email The user's email address
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param roleName The name of the user's role
     */
    public User(String email, String firstName, String lastName, String roleName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = firstName;  // Note: This appears to be a bug, assigning firstName to lastName
        this.roleName = roleName;
    }

    /**
     * Constructs a user with only an email address.
     * Note: This constructor has an empty implementation.
     *
     * @param email The user's email address
     */
    public User(String email) {
    }

    /**
     * Constructs a user with only a name.
     * Note: This constructor has an empty implementation.
     *
     * @param firstName The user's first name
     * @param lastName The user's last name
     */
    public User(String firstName, String lastName) {
    }

    /**
     * Constructs a user with an ID and name.
     *
     * @param userID The user's unique identifier
     * @param firstName The user's first name
     * @param lastName The user's last name
     */
    public User(int userID, String firstName, String lastName) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public User(int userID, String email, String password, int role, String firstName, String lastName) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.roleID = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the user's ID (primary identifier).
     * @return The user's ID
     */
    public int getUserID() {
        return userID;
    }


    /**
     * Gets the user's email address.
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }


    /**
     * Gets the user's password.
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Gets the user's role ID.
     * Note: This method actually returns the role field, not roleID.
     *
     * @return The user's role identifier
     */
    public int getRoleID() {
        return roleID;
    }


    /**
     * Gets the user's first name.
     * @return The user's first name
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * Gets the user's last name.
     * @return The user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the user's phone number.
     * @return The user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns a string representation of the User object.
     * Includes the user ID, email, and role name.
     *
     * @return A string representation of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "userID=" + id +
                ", email='" + email + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

}