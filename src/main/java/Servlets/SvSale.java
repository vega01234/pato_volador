package Servlets;

import DAO.SaleDAO;
import EntityClass.Sale;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvSale", urlPatterns = {"/SvSale"})
public class SvSale extends HttpServlet {

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
        
        // Method to Use
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createSale(request, response);
                break;
            case "search":
                searchSale(request, response);
            default:
                System.out.println("Aca No Paso Nada :)");
                break;
        }
    }

    // Create Sale Using Form Fields
    public void createSale(HttpServletRequest request, HttpServletResponse response) {
        // Form Fields 
        String id_sale = request.getParameter("no_sale");
        String rfc_customer = request.getParameter("rfc_customer");
        String rfc_employee = request.getParameter("rfc_employee");
        String id_product = request.getParameter("id_product");
        String description = request.getParameter("description");
        String units = request.getParameter("units");
        String unit_price = request.getParameter("unit_price");
        String import_product = request.getParameter("import_product");
        String iva_product = request.getParameter("iva_product");
        String subtotal = request.getParameter("subtotal");
        String iva_total = request.getParameter("iva_total");
        String total = request.getParameter("total");
    }
    
    public void searchSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Form Fields
        String no_sale = request.getParameter("no_sale");
        
        // Convert Variables
        int id_sale = Integer.parseInt(no_sale);
        
        // Create SaleDAO to Search Sale with ID Sale
        Sale sale = new Sale();
        SaleDAO saleDAO = new SaleDAO();
        sale = saleDAO.getSaleById(id_sale);
        
        // Set Atributes and Pass to Info Page
        HttpSession session = request.getSession();
        session.setAttribute("sale", sale);
        
        // Redirect to Info Page
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
