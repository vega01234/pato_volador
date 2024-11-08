package EntityClassTest;

import DAO.CustomerDAO;
import DAO.UserDAO;
import EntityClass.Customer;
import EntityClass.User;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerDAOTest {
    
    private CustomerDAO customerDAO;
    private UserDAO userDAO;
    
    public CustomerDAOTest() {
    }
    
    @BeforeEach
    public void setUp() {
        customerDAO = new CustomerDAO();
        userDAO = new UserDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void createCustomer() throws SQLException {
        // Default Values
        String rfc_customer = "RFC_Customer2";
                
        // Create User To Insert New Customer
        User user = new User(0, rfc_customer, rfc_customer, 2);
        int generatedID = userDAO.createUser(user);
        
        // Create Customer Using the ID Returned
        LocalDate timeTest = LocalDate.now();
        Customer customer = new Customer(rfc_customer, generatedID, "Fernando Vega", "CURP Customer", timeTest, "Mexicano", "En Mi Casa", "Soltero", "Estudiante", "Bachillerato", true);
        customerDAO.createCustomer(customer);
        
        // Get Data By The RFC
        Customer customerDB = customerDAO.getCustomerByRfc(rfc_customer);
        assertNotNull(customerDB);
    }
}
