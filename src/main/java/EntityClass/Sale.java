package EntityClass;

import java.time.LocalDate;

public class Sale {
    
    private int id_sale; // Primary Key in Sales Table
    private LocalDate date_sale;
    private float subtotal_sale;
    private float iva_sale;
    private float total_sale;
    private String rfc_employee; // Foreign Key in Employees Table
    private String rfc_customer; //  Foreign Key in Customers Table
    
    
    // Constructors
    public Sale() {
    }

    public Sale(int id_sale, LocalDate date_sale, float subtotal_sale, float iva_sale, float total_sale, String rfc_employee, String rfc_customer) {
        this.id_sale = id_sale;
        this.date_sale = date_sale;
        this.subtotal_sale = subtotal_sale;
        this.iva_sale = iva_sale;
        this.total_sale = total_sale;
        this.rfc_employee = rfc_employee;
        this.rfc_customer = rfc_customer;
    }
    
    //Getters and Setters
    public int getId_sale() {
        return id_sale;
    }

    public void setId_sale(int id_sale) {
        this.id_sale = id_sale;
    }

    public LocalDate getDate_sale() {
        return date_sale;
    }

    public void setDate_sale(LocalDate date_sale) {
        this.date_sale = date_sale;
    }

    public float getSubtotal_sale() {
        return subtotal_sale;
    }

    public void setSubtotal_sale(float subtotal_sale) {
        this.subtotal_sale = subtotal_sale;
    }

    public float getIva_sale() {
        return iva_sale;
    }

    public void setIva_sale(float iva_sale) {
        this.iva_sale = iva_sale;
    }

    public float getTotal_sale() {
        return total_sale;
    }

    public void setTotal_sale(float total_sale) {
        this.total_sale = total_sale;
    }

    public String getRfc_employee() {
        return rfc_employee;
    }

    public void setRfc_employee(String rfc_employee) {
        this.rfc_employee = rfc_employee;
    }

    public String getRfc_customer() {
        return rfc_customer;
    }

    public void setRfc_customer(String rfc_customer) {
        this.rfc_customer = rfc_customer;
    }
    
}
