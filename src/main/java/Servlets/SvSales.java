package Servlets;

import DAO.SaleDAO;
import EntityClass.Sale;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvSales", urlPatterns = {"/SvSales"})
@MultipartConfig
public class SvSales extends HttpServlet {

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
                    createSale(request, response);
                    break;
                case "search":
                    searchSale(request, response);
                    break;
                default:
                    System.out.println("Aca No Paso Nada :)");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void createSale(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Form Fields
        LocalDate date_now = LocalDate.now();
        String id_product = request.getParameter("id_product");
        String units = request.getParameter("units");
        String subtotal_sale = request.getParameter("subtotal");
        String iva_total = request.getParameter("iva_total");
        String total = request.getParameter("total");
        String rfc_employee = request.getParameter("rfc_employee");
        String rfc_customer = request.getParameter("rfc_customer");
                
        // Conversion Variables 
        int id_product_converted = Integer.parseInt(id_product);
        int units_converted = Integer.parseInt(units);
        float subtotal_sale_converted = Float.parseFloat(subtotal_sale);
        float iva_sale_converted = Float.parseFloat(iva_total);
        float total_sale_converted = Float.parseFloat(total);
        
        // Create Sale Using Form Data
        Sale sale = new Sale(0, date_now, id_product_converted, units_converted, subtotal_sale_converted, iva_sale_converted, total_sale_converted, rfc_employee, rfc_customer);
        SaleDAO saleDAO = new SaleDAO();
        
        // Set Attribute and Pass to Info Page
        HttpSession session = request.getSession();
        
        // call Create Method in SaleDAO
        int statudCreated = saleDAO.createSale(sale);
        // Set Message Based on Result
        if (statudCreated > 0) {
            session.setAttribute("message", "Registro Exitoso");
        } else {
            session.setAttribute("message", "Error en el Registro");
        }
        // Redirect User
        response.sendRedirect("system_pages/sales/register_sale.jsp");
        
    }
    
    public void searchSale(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Form Fields
        String id_sale = request.getParameter("no_sale");
        int no_sale = Integer.parseInt(id_sale);
        
        // Create SaleDAO to Search Sale with No Sale
        Sale sale = new Sale();
        SaleDAO saleDAO = new SaleDAO();
        sale = saleDAO.getSaleById(no_sale);
        
        // Set Attributes and pass to Info Page
        HttpSession session = request.getSession();
        session.setAttribute("sale", sale);
        
        // Redirect User
        response.sendRedirect("system_pages/sales/sale_info.jsp");
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
