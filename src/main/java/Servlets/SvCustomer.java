package Servlets;

import DAO.CustomerDAO;
import DAO.UserDAO;
import EntityClass.Customer;
import EntityClass.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvCustomer", urlPatterns = {"/SvCustomer"})
@MultipartConfig
public class SvCustomer extends HttpServlet {    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Method to Use
            String action = request.getParameter("action");
            
            switch (action) {
                case "create":
                    createCustomer(request, response);
                    break;
                case "search":
                    seachCustomer(request, response);
                    break;
                case "update":
                    deleteCustomer(request, response);
                    break;
                default:
                    System.out.println("Aca No Paso Nada :)");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Create Customer Using Form Fields
    private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Form Fields
        String rfc_customer = request.getParameter("rfc");
        String name_customer = request.getParameter("full_name");
        String curp_customer = request.getParameter("curp");
        String date_birth_customer = request.getParameter("birth_date");
        String nacionality_customer = request.getParameter("nacionality");
        String adress_customer = request.getParameter("adress");
        String civil_state_customer = request.getParameter("civil_state");
        String profession_customer = request.getParameter("ocupation");
        String degree_study_customer = request.getParameter("study_degree");
        
        // Conversion of Varianbles
        LocalDate date_birth_conversion = LocalDate.parse(date_birth_customer);

        // Create User Using RFC For Username and Password
        User user = new User(0, rfc_customer, rfc_customer, 2);
        UserDAO userDAO = new UserDAO();
        int id_user = userDAO.createUser(user);

        // Create Customer Using the ID Returned
        Customer customer = new Customer(rfc_customer, id_user, name_customer, curp_customer, date_birth_conversion, nacionality_customer, adress_customer, civil_state_customer, profession_customer, degree_study_customer);
        CustomerDAO customerDAO = new CustomerDAO();
        
        // Set Attributes and Pass to Info Page
        HttpSession session = request.getSession();
        
        try {
            // Call Create Method in CustomerDAO
            boolean isCreated = customerDAO.createCustomer(customer);
                
            // Set Appropriate Message Based on Result
            if (isCreated) {
                session.setAttribute("message", "Registro Exitoso");
            } else {
                session.setAttribute("message", "Error en el Registro");
            }
            
            // Redirect to the Other Servlet
            response.sendRedirect("system_pages/customer/register_customer.jsp");
            
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "Error al Procesar la Solicitud");
            response.sendRedirect("system_pages/customer/register_customer.jsp");
        }
    }
    
    // Search Customer By RFC 
    private void seachCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // Form Fields 
        String rfc_customer = request.getParameter("rfc");
        
        // Create CustomerDAO to Search Customer with RFC
        Customer customer = new Customer();
        CustomerDAO customerDAO = new CustomerDAO();
        customer = customerDAO.getCustomerByRfc(rfc_customer);
        
        // Set Attributes and Pass to Info Page
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);
        
        // Redirect to the Other Servlet
        response.sendRedirect("system_pages/customer/customer_info.jsp");
    }
    
    // Delete Customer by RFC
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
