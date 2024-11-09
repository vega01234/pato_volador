package Servlets;

import DAO.CustomerDAO;
import DAO.UserDAO;
import EntityClass.Customer;
import EntityClass.User;
import ValidFiles.VerFir;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

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
                    searchCustomer(request, response);
                    break;
                case "update":
                    updateCustomer(request, response);
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

        // Config Files Upload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        // List to Save Files Path 
        List<String> rutasArchivos = new ArrayList<>();
        Map<String, String> camposFormulario = new HashMap<>();

        HttpSession session = request.getSession();

        try {
            // Create RequestContext From HttpServletRequest
            RequestContext context = new ServletRequestContext(request);

            // Process Request to Get Fields and Files
            List<FileItem> items = upload.parseRequest(context);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Add Text Fields to Map
                    camposFormulario.put(item.getFieldName(), item.getString());
                } else {
                    // Save Files in Path
                    String rutaArchivo = "C:\\TEMPORALES\\" + item.getName();
                    File archivo = new File(rutaArchivo);
                    item.write(archivo);
                    rutasArchivos.add(rutaArchivo);
                }
            }

            // Valid Files 
            if (rutasArchivos.size() >= 3) {
                String rutaClavePublica = rutasArchivos.get(0);
                String rutaFirmaDigital = rutasArchivos.get(1);
                String rutaDocumentoPPA = rutasArchivos.get(2);

                // Valid Files Logic
                VerFir validador = new VerFir();
                boolean archivoValido = validador.VerFir(rutaClavePublica, rutaFirmaDigital, rutaDocumentoPPA);

                if (!archivoValido) {
                    session.setAttribute("message", "Archivo no válido");
                    response.sendRedirect("system_pages/customer/register_customer.jsp");
                    return;
                }
            } else {
                session.setAttribute("message", "No se subieron todos los archivos requeridos");
                response.sendRedirect("system_pages/customer/register_customer.jsp");
                return;
            }

            // Form Fields
            String rfc_customer = camposFormulario.get("rfc");
            String name_customer = camposFormulario.get("full_name");
            String curp_customer = camposFormulario.get("curp");
            String date_birth_customer = camposFormulario.get("birth_date");
            String nacionality_customer = camposFormulario.get("nacionality");
            String adress_customer = camposFormulario.get("adress");
            String civil_state_customer = camposFormulario.get("civil_state");
            String profession_customer = camposFormulario.get("ocupation");
            String degree_study_customer = camposFormulario.get("study_degree");

            // Validations for required fields
            if (rfc_customer == null || name_customer == null || curp_customer == null || date_birth_customer == null) {
                session.setAttribute("message", "Campos obligatorios faltantes");
                response.sendRedirect("system_pages/customer/register_customer.jsp");
                return;
            }

            // Convert Date
            LocalDate date_birth_conversion = LocalDate.parse(date_birth_customer);   

            // Create User and Customer
            User user = new User(0, rfc_customer, rfc_customer, 2);
            UserDAO userDAO = new UserDAO();
            int id_user = userDAO.createUser(user);

            Customer customer = new Customer(rfc_customer, id_user, name_customer, curp_customer, date_birth_conversion, nacionality_customer, adress_customer, civil_state_customer, profession_customer, degree_study_customer);
            CustomerDAO customerDAO = new CustomerDAO();

            // Create Customer and Get Message
            boolean isCreated = customerDAO.createCustomer(customer);
            if (isCreated) {
                session.setAttribute("message", "Registro Exitoso");
            } else {
                session.setAttribute("message", "Error en el Registro");
            }

            response.sendRedirect("system_pages/customer/register_customer.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Error al Procesar la Solicitud");
            response.sendRedirect("system_pages/customer/register_customer.jsp");
        }
    }

    
    // Search Customer By RFC 
    private void searchCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // Form Fields 
        String rfc_customer = request.getParameter("rfc");
        String type_form = request.getParameter("type_form");
        
        // Create CustomerDAO to Search Customer with RFC
        Customer customer = new Customer();
        CustomerDAO customerDAO = new CustomerDAO();
        customer = customerDAO.getCustomerByRfc(rfc_customer);
        
        // Set Attributes and Pass to Info Page
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);
        
        // Redirect to the Other Servlet 
        switch (type_form) {
            case "form_search":
                response.sendRedirect("system_pages/customer/customer_info.jsp");
                break;
            case "form_update":
                response.sendRedirect("system_pages/customer/modify_customer_data.jsp");
                break;
            default:
                response.sendRedirect("index.jsp");
        }        
    }
    
    // Delete Customer by RFC
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Form Fields
        String rfc_customer = request.getParameter("rfc");
        String id_user = request.getParameter("id_user");
        String name_customer = request.getParameter("full_name");
        String curp_customer = request.getParameter("curp");
        String date_birth_customer = request.getParameter("birth_date");
        String nacionality_customer = request.getParameter("nacionality");
        String adress_customer = request.getParameter("adress");
        String civil_state_customer = request.getParameter("civil_state");
        String profession_customer = request.getParameter("ocupation");
        String degree_study_customer = request.getParameter("study_degree");
        
        // Conversion of Variables
        int id_user_conversion = Integer.parseInt(id_user);
        LocalDate date_birth_conversion = LocalDate.parse(date_birth_customer);
        
        // Create New Customer With Form Values 
        Customer updatedCustomer = new Customer(rfc_customer, id_user_conversion, name_customer, curp_customer, date_birth_conversion, nacionality_customer, adress_customer, civil_state_customer, profession_customer, degree_study_customer);
        
        // Create New CustomerDAO to Update Data
        CustomerDAO customerDAO = new CustomerDAO();
        HttpSession session = request.getSession();
        
        try {
            // Call Update Method
            boolean isUpdated = customerDAO.updateCustomer(updatedCustomer);
            
            // Return Message
            if (isUpdated) {
                session.setAttribute("message", "Actualizacion Exitosa");
            } else {
                session.setAttribute("message", "Error en la Actualizacion");
            }
            
            // Redirect to Modify Customer Page
            response.sendRedirect("system_pages/customer/modify_customer.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("message", "Error al Procesar la Solicitud");
            response.sendRedirect("system_pages/customer/modify_customer.jsp");
        }
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
