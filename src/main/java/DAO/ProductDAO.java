package DAO;

import DatabaseConnection.MySQLConnection;
import EntityClass.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    
    // Method to Crate Product in Database
    public boolean createProduct(Product product) {
        String sql = "INSERT INTO products (name_product, price_product, img_path_product) VALUES (?, ?, ?)";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName_product());
            stmt.setFloat(2, product.getPrice_product());
            stmt.setString(3, product.getImg_path_product());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to Read a Product in Database
    public Product getProductById(int id_product) {
        String sql = "SELECT * FROM products WHERE id_product = ?";
        Product product = null;
        try  {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_product);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId_product(rs.getInt("id_product"));
                product.setName_product(rs.getString("name_product"));
                product.setPrice_product(rs.getFloat("price_product"));
                product.setImg_path_product(rs.getString("img_path_product"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    
    // Method to Update Product in Database
    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET name_product = ?, price_product = ?, img_path_product = ? WHERE id_product = ?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName_product());
            stmt.setFloat(2, product.getPrice_product());
            stmt.setString(3, product.getImg_path_product());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to Delete Customer by ID in Database
    public boolean deleteProduct(int id_product) {
        String sql = "DELETE FROM products WHERE id_product = ?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_product);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to Show All Product Registers in Database
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            Connection conn = MySQLConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product();
                product.setId_product(rs.getInt("id_product"));
                product.setName_product(rs.getString("name_product"));
                product.setPrice_product(rs.getFloat("price_product"));
                product.setImg_path_product(rs.getString("img_path_product"));
                products.add(product);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
}
