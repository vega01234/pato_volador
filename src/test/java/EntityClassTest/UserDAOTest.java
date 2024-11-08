package EntityClassTest;

import DAO.UserDAO;
import EntityClass.User;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDAOTest {
    
    private UserDAO userDAO;
    
    public UserDAOTest() {
    }
    
    @BeforeEach
    public void setUp() {
        userDAO = new UserDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }
    
//    @Test
//    public void createUserTest() throws SQLException {
//        User user = new User(0, "Username", "Password", 1);
//        int generatedID = userDAO.createUser(user);
//        
//        // Valid Generated ID is Greater Than 0
//        assertTrue(generatedID > 0);
//        
//        // Get Data By The Returned ID
//        User userDB = userDAO.getUserById(generatedID);
//        assertNotNull(userDB);
//    }
}
