package DAO;

import DatabaseConnection.MySQLConnection;
import EntityClass.Employee;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    // Method to Create Employee in Database 
    public boolean createEmployee(Employee employee) {
        String sql = "INSERT INTO employees (rfc_employee, id_user, name_employee, position, salary, hire_date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getRfc_customer());
            stmt.setInt(2, employee.getId_user());
            stmt.setString(3, employee.getName_employee());
            stmt.setString(4, employee.getPosition());
            stmt.setFloat(5, employee.getSalary());
            
            // Date Conversion
            LocalDate hire_date_conversion = employee.getHire_date();
            
            stmt.setDate(6, Date.valueOf(hire_date_conversion));
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return True If SQL Statement was Successfuly
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error in SQL Statement
        }
    }
    
    // Method to Read a Employee in Database 
    public Employee getEmployeeByRfc(String rfc) {
        String sql = "SELECT * FROM employees WHERE rfc_employee = ?";
        Employee employee = null;
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rfc);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setRfc_customer(rs.getString("rfc_customer"));
                employee.setId_user(rs.getInt("id_user"));
                employee.setName_employee(rs.getString("name_employee"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getFloat("salary"));
                employee.setHire_date(rs.getDate("hire_date").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    
    // Method to Update Employee in 
    public boolean updayeEmployee(Employee employee) {
        String sql = "UPDATE employees SET rfc_customer = ?, name_employee = ?, position = ?, salary = ?, hire_date = ? WHERE rfc_employee = ?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getRfc_customer());
            stmt.setString(2, employee.getName_employee());
            stmt.setString(3, employee.getPosition());
            stmt.setFloat(4, employee.getSalary());
            stmt.setDate(5, Date.valueOf(employee.getHire_date()));
            stmt.setString(6, employee.getRfc_customer());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to Delete Employee by RFC in Database 
    public boolean deleteCustomer(String rfc) {
        String sql = "DELETE FROM employees WHERE rfc_customer = ?";
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rfc);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to Show All Employeed Registers in Database 
    public List<Employee> getAllEmployee(){
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try {
            Connection conn = MySQLConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Employee employee = new Employee();
                employee.setRfc_customer(rs.getString("rfc_customer"));
                employee.setId_user(rs.getInt("id_user"));
                employee.setName_employee(rs.getString("name_employee"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getFloat("salary"));
                employee.setHire_date(rs.getDate("hire_date").toLocalDate());
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
