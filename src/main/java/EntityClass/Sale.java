package EntityClass;

import java.time.LocalDate;

public class Sale {

    private int id_sale;
    private LocalDate date_sale;
    private int id_product;
    private int units;
    private float subtotal_sale;
    private float iva_sale;
    private float total_sale;
    private String rfc_employee;
    private String rfc_customer;
    
    // Constructors
    public Sale() {
    }

    public Sale(int id_sale, LocalDate date_sale, int id_product, int units, float subtotal_sale, float iva_sale, float total_sale, String rfc_employee, String rfc_customer) {
        this.id_sale = id_sale;
        this.date_sale = date_sale;
        this.id_product = id_product;
        this.units = units;
        this.subtotal_sale = subtotal_sale;
        this.iva_sale = iva_sale;
        this.total_sale = total_sale;
        this.rfc_employee = rfc_employee;
        this.rfc_customer = rfc_customer;
    }
    
    // Getters and Setters
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

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
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
