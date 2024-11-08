package EntityClass;

public class User {
    
    private int id_user;
    private String username; // RFC Customer or Employee
    private String password; // RFC Customer or Employee
    private int id_type_user; // Customer or Employee
    
    // Constructors
    public User() {
    }

    public User(int id_user, String username, String password, int id_type_user) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.id_type_user = id_type_user;
    }
    
    // Getters and Setters
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_type_user() {
        return id_type_user;
    }

    public void setId_type_user(int id_type_user) {
        this.id_type_user = id_type_user;
    }
    
}
