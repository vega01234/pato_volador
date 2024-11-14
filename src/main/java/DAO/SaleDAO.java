package DAO;

import DatabaseConnection.MySQLConnection;
import EntityClass.Sale;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    // Method to Create Sale in Database
    public int createSale(Sale sale) {
        String sql = "INSERT INTO sales (date_sale, id_product, units, subtotal_sale, iva_sale, total_sale, rfc_employee, rfc_customer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1;
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            LocalDate date_sale_converted = sale.getDate_sale();
            stmt.setDate(1, Date.valueOf(date_sale_converted));
            stmt.setInt(2, sale.getId_product());
            stmt.setInt(3, sale.getUnits());
            stmt.setFloat(4, sale.getSubtotal_sale());
            stmt.setFloat(5, sale.getIva_sale());
            stmt.setFloat(6, sale.getTotal_sale());
            stmt.setString(7, sale.getRfc_employee());
            stmt.setString(8, sale.getRfc_customer());
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
        return generatedId;
    }
    
    // Method to Read a Sale in Database
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
                sale.setId_product(rs.getInt("id_sale"));
                sale.setDate_sale(rs.getDate("date_sale").toLocalDate());
                sale.setId_product(rs.getInt("id_product"));
                sale.setUnits(rs.getInt("units"));
                sale.setSubtotal_sale(rs.getFloat("subtotal_sale"));
                sale.setIva_sale(rs.getFloat("iva_sale"));
                sale.setTotal_sale(rs.getFloat("total_sale"));
                sale.setRfc_employee(rs.getString("rfc_employee"));
                sale.setRfc_customer(rs.getString("rfc_customer"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }

    // Method to Show All Sales Registers in Database
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales";
        try {
            Connection conn = MySQLConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Sale sale = new Sale();
                sale.setId_product(rs.getInt("id_sale"));
                sale.setDate_sale(rs.getDate("date_sale").toLocalDate());
                sale.setId_product(rs.getInt("id_product"));
                sale.setUnits(rs.getInt("units"));
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
