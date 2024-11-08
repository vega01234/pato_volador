package DAO;

import DatabaseConnection.MySQLConnection;
import EntityClass.Customer;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    
    // Method to Create Customer in Database
    public void createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (rfc_customer, id_user, name_customer, curp_customer, date_birth_customer, nacionality_customer, adress_customer, civil_state_customer, profession_customer, degree_study_customer, certificate_validation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customer.getRfc_customer());
            stmt.setInt(2, customer.getId_user());
            stmt.setString(3, customer.getName_customer());
            stmt.setString(4, customer.getCurp_customer());
            
            LocalDate birth_date = customer.getDate_birth_customer();
            
            stmt.setDate(5, Date.valueOf(birth_date));
            stmt.setString(6, customer.getNacionality_customer());
            stmt.setString(7, customer.getAdress_customer());
            stmt.setString(8, customer.getCivil_state_customer());
            stmt.setString(9, customer.getProfession_customer());
            stmt.setString(10, customer.getDegree_study_customer());
            stmt.setBoolean(11, customer.isCertificate_validation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to Read a Customer in Database
    public Customer getCustomerByRfc(String rfc) {
        String sql = "SELECT * FROM customers WHERE rfc_customer = ?";
        Customer customer = null;
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rfc);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setRfc_customer(rs.getString("rfc_customer"));
                customer.setId_user(rs.getInt("id_user"));
                customer.setName_customer(rs.getString("name_customer"));
                customer.setCurp_customer(rs.getString("curp_customer"));
                customer.setDate_birth_customer(rs.getDate("date_birth_customer").toLocalDate());
                customer.setNacionality_customer(rs.getString("nacionality_customer"));
                customer.setAdress_customer(rs.getString("adress_customer"));
                customer.setCivil_state_customer(rs.getString("civil_state_customer"));
                customer.setProfession_customer(rs.getString("profession_customer"));
                customer.setDegree_study_customer(rs.getString("degree_study_customer"));
                customer.setCertificate_validation(rs.getBoolean("certificate_validation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    
    // Method to Delete Customer by RFC in Database 
    public void deleteCustomer(String rfc) {
        String sql = "DELETE FROM customers WHERE rfc_customer = ?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rfc);
            stmt.executeUpdate();
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }
    
    // Method to Show All Customers Registers in Database 
    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try {
            Connection conn = MySQLConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setRfc_customer(rs.getString("rfc_customer"));
                customer.setId_user(rs.getInt("id_user"));
                customer.setName_customer(rs.getString("name_customer"));
                customer.setCurp_customer(rs.getString("curp_customer"));
                customer.setDate_birth_customer(rs.getDate("date_birth_customer").toLocalDate());
                customer.setNacionality_customer(rs.getString("nacionality_customer"));
                customer.setAdress_customer(rs.getString("adress_customer"));
                customer.setCivil_state_customer(rs.getString("civil_state_customer"));
                customer.setProfession_customer(rs.getString("profession_customer"));
                customer.setDegree_study_customer(rs.getString("degree_study_customer"));
                customer.setCertificate_validation(rs.getBoolean("certificate_validation"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
