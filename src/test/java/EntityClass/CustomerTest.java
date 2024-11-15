package EntityClass;

import DAO.CustomerDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    
    private CustomerDAO customerDAO;
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        customerDAO = new CustomerDAO();

        // Configuración de un cliente de prueba
        testCustomer = new Customer();
        testCustomer.setRfc_customer("TEST123456789");
        testCustomer.setId_user(1);
        testCustomer.setName_customer("Test Customer");
        testCustomer.setCurp_customer("CURP123456789");
        testCustomer.setDate_birth_customer(LocalDate.of(1990, 1, 1));
        testCustomer.setNacionality_customer("Mexican");
        testCustomer.setAdress_customer("123 Test Street");
        testCustomer.setCivil_state_customer("Single");
        testCustomer.setProfession_customer("Engineer");
        testCustomer.setDegree_study_customer("Bachelor's");
    }

    @Test
    void testCreateCustomer() throws SQLException {
        // Prueba de creación de un cliente
        boolean isCreated = customerDAO.createCustomer(testCustomer);
        assertTrue(isCreated, "El cliente debería haberse creado correctamente.");
        
        // Verificar si el cliente fue agregado a la base de datos
        Customer createdCustomer = customerDAO.getCustomerByRfc(testCustomer.getRfc_customer());
        assertNotNull(createdCustomer, "El cliente creado debería existir en la base de datos.");
        assertEquals(testCustomer.getName_customer(), createdCustomer.getName_customer());
    }

    @Test
    void testGetCustomerByRfc() throws SQLException {
        // Asegurarse de que el cliente existe
        customerDAO.createCustomer(testCustomer);

        // Prueba de obtención de un cliente por RFC
        Customer retrievedCustomer = customerDAO.getCustomerByRfc(testCustomer.getRfc_customer());
        assertNotNull(retrievedCustomer, "El cliente debería existir en la base de datos.");
        assertEquals(testCustomer.getRfc_customer(), retrievedCustomer.getRfc_customer());
        assertEquals(testCustomer.getName_customer(), retrievedCustomer.getName_customer());
    }

    @Test
    void testUpdateCustomer() throws SQLException {
        // Crear el cliente inicial
        customerDAO.createCustomer(testCustomer);

        // Modificar valores del cliente
        testCustomer.setName_customer("Updated Customer");
        testCustomer.setAdress_customer("456 Updated Street");

        // Actualizar el cliente en la base de datos
        boolean isUpdated = customerDAO.updateCustomer(testCustomer);
        assertTrue(isUpdated, "El cliente debería haberse actualizado correctamente.");

        // Verificar si el cliente fue actualizado
        Customer updatedCustomer = customerDAO.getCustomerByRfc(testCustomer.getRfc_customer());
        assertEquals("Updated Customer", updatedCustomer.getName_customer());
        assertEquals("456 Updated Street", updatedCustomer.getAdress_customer());
    }

    @Test
    void testDeleteCustomer() throws SQLException {
        // Crear el cliente inicial
        customerDAO.createCustomer(testCustomer);

        // Eliminar el cliente
        customerDAO.deleteCustomer(testCustomer.getRfc_customer());

        // Verificar que el cliente ya no existe en la base de datos
        Customer deletedCustomer = customerDAO.getCustomerByRfc(testCustomer.getRfc_customer());
        assertNull(deletedCustomer, "El cliente debería haber sido eliminado de la base de datos.");
    }

    @Test
    void testGetAllCustomers() throws SQLException {
        // Asegurarse de que hay al menos un cliente en la base de datos
        customerDAO.createCustomer(testCustomer);

        // Prueba para obtener todos los clientes
        List<Customer> customers = customerDAO.getAllCustomer();
        assertFalse(customers.isEmpty(), "La lista de clientes no debería estar vacía.");
        
        // Verificar que nuestro cliente de prueba esté en la lista
        boolean customerExists = customers.stream()
            .anyMatch(customer -> customer.getRfc_customer().equals(testCustomer.getRfc_customer()));
        assertTrue(customerExists, "El cliente de prueba debería existir en la lista de todos los clientes.");
    }

    @AfterEach
    void tearDown() {
        // Limpiar la base de datos de prueba eliminando el cliente de prueba después de cada prueba
        customerDAO.deleteCustomer(testCustomer.getRfc_customer());
    }
    
}
