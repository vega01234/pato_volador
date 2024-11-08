package DAO;

import DatabaseConnection.MySQLConnection;
import EntityClass.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    // Method to Create User in Database
    public int createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, id_type_user) VALUES (?, ?, ?)";
        int generatedId = -1;
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId_type_user());
            stmt.executeUpdate();
            
            // Get Generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId; // Return Generated User ID
    }
    
    // Method to Read a User in Database
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id_user = ?";
        User user = null;
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_type_user(rs.getInt("id_type_user"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // Method to Update User in Database
    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, id_type_user = ? WHERE id_user = ?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId_type_user());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to Delete User by ID in Database
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id_user = ?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to Show All User Registers in Database
    public List<User> getAllUser() {
       List<User> users  = new ArrayList<>();
       String sql = "SELECT * FROM users";
       try {
            Connection conn = MySQLConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_type_user(rs.getInt("id_type_user"));
                users.add(user);
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return users;
    }
    
}
