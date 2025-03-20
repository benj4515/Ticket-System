package dk.easv.ticket_system.BE;

public class User {
    private int id;
    private String email;
    private String password;
    private String role;

    public User(String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

