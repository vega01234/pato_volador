package EntityClass;

import java.time.LocalDate;

public class Customer {
    
    private String rfc_customer; // Primary Key in Customer Table
    private int id_user; // Foreign Key in Users Table
    private String name_customer;
    private String curp_customer;
    private LocalDate date_birth_customer;
    private String nacionality_customer;
    private String adress_customer;
    private String civil_state_customer;
    private String profession_customer;
    private String degree_study_customer;
    
    // Constructors 
    public Customer() {   
    }

    public Customer(String rfc_customer, int id_user, String name_customer, String curp_customer, LocalDate date_birth_customer, String nacionality_customer, String adress_customer, String civil_state_customer, String profession_customer, String degree_study_customer) {
        this.rfc_customer = rfc_customer;
        this.id_user = id_user;
        this.name_customer = name_customer;
        this.curp_customer = curp_customer;
        this.date_birth_customer = date_birth_customer;
        this.nacionality_customer = nacionality_customer;
        this.adress_customer = adress_customer;
        this.civil_state_customer = civil_state_customer;
        this.profession_customer = profession_customer;
        this.degree_study_customer = degree_study_customer;
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

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public String getCurp_customer() {
        return curp_customer;
    }

    public void setCurp_customer(String curp_customer) {
        this.curp_customer = curp_customer;
    }

    public LocalDate getDate_birth_customer() {
        return date_birth_customer;
    }

    public void setDate_birth_customer(LocalDate date_birth_customer) {
        this.date_birth_customer = date_birth_customer;
    }

    public String getNacionality_customer() {
        return nacionality_customer;
    }

    public void setNacionality_customer(String nacionality_customer) {
        this.nacionality_customer = nacionality_customer;
    }
    
    public String getAdress_customer() {
        return adress_customer;
    }

    public void setAdress_customer(String adress_customer) {
        this.adress_customer = adress_customer;
    }

    public String getCivil_state_customer() {
        return civil_state_customer;
    }

    public void setCivil_state_customer(String civil_state_customer) {
        this.civil_state_customer = civil_state_customer;
    }

    public String getProfession_customer() {
        return profession_customer;
    }

    public void setProfession_customer(String profession_customer) {
        this.profession_customer = profession_customer;
    }

    public String getDegree_study_customer() {
        return degree_study_customer;
    }

    public void setDegree_study_customer(String degree_study_customer) {
        this.degree_study_customer = degree_study_customer;
    }
    
}
