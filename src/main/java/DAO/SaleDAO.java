package DAO;

import DatabaseConnection.MySQLConnection;
import EntityClass.Sale;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {
    
    // Method to Create Sale in Database 
    public boolean createSale(Sale sale) {
        String sql = "INSERT INTO sales (date_sale, subtotal_sale, iva_sale, total_sale, rfc_employee, rfc_customer) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            // Variable Conversion
            LocalDate date_sale_convert = sale.getDate_sale();

            stmt.setDate(1, Date.valueOf(date_sale_convert));
            stmt.setFloat(2, sale.getSubtotal_sale());
            stmt.setFloat(3, sale.getId_sale());
            stmt.setFloat(4, sale.getTotal_sale());
            stmt.setString(5, sale.getRfc_employee());
            stmt.setString(6, sale.getRfc_customer());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to Read Sale in Database
    public Sale getSaleById(int id_sale) {
        String sql = "SELECT * FROM sales WHERE id_sale = ?";
        Sale sale = null;
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_sale);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sale = new Sale();
                sale.setId_sale(rs.getInt("id_sale"));
                sale.setDate_sale(rs.getDate("date_sale").toLocalDate());
                sale.setSubtotal_sale(rs.getFloat("subtotal_sale"));
                sale.setIva_sale(rs.getFloat("iva_sale"));
                sale.setTotal_sale(rs.getFloat("total_sale"));
                sale.setRfc_employee(rs.getString("rfc_employee"));
                sale.setRfc_customer(rs.getString("rfc_customer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }
    
    // Method to Delete Sale by ID in Database
    public boolean deleteSale(int id_sale) {
        String sql = "DELETE FROM sales WHERE id_sale = ?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_sale);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to Show All Sales in Database
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "";
        try {
            Connection conn = MySQLConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setId_sale(rs.getInt("id_sale"));
                sale.setDate_sale(rs.getDate("date_sale").toLocalDate());
                sale.setSubtotal_sale(rs.getFloat("subtotal_sale"));
                sale.setIva_sale(rs.getFloat("iva_sale"));
                sale.setTotal_sale(rs.getFloat("total_sale"));
                sale.setRfc_employee(rs.getString("rfc_employee"));
                sale.setRfc_customer(rs.getString("rfc_customer"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }
    
}
