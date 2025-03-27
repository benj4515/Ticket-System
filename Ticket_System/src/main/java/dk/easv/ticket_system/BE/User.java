package dk.easv.ticket_system.BE;

public class User {
    private int id;
    private String email;
    private String password;
    private int role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String roleName;


    public User(String email, String password, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    public User(String email, String password, int role, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public User(String email, String password, int role, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }

    public User (String email,int role, String firstName, String lastName) {
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    

    public User(int generatedUserID, String email, String password,
                int roleID, String firstName, String lastName, String phoneNumber) {
    }

    public User(int id, String email, String password, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String firstName, String lastName) {
    }

    public User(String email, String firstName, String lastName, String roleName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = firstName;
        this.roleName = roleName;
    }

    public int getUserId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return role;
    }

    public void setRoleID(int role) {
        this.role = role;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

