package EntityClass;

import java.time.LocalDate;

public class Employee {
    
    private String rfc_customer;
    private int id_user;
    private String name_employee;
    private String position;
    private float salary;
    private LocalDate hire_date;
    
    // Constructors
    public Employee() {
    }

    public Employee(String rfc_customer, int id_user, String name_employee, String position, float salary, LocalDate hire_date) {
        this.rfc_customer = rfc_customer; // Primary Key in Employee Table
        this.id_user = id_user; // Foreign Key in Users Table
        this.name_employee = name_employee;
        this.position = position;
        this.salary = salary;
        this.hire_date = hire_date;
    }
    
    // Getters and Setters
    public String getRfc_customer() {
        return rfc_customer;
    }

    public void setRfc_customer(String rfc_customer) {
        this.rfc_customer = rfc_customer;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_employee() {
        return name_employee;
    }

    public void setName_employee(String name_employee) {
        this.name_employee = name_employee;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }
    
    
}
