<%@page import="java.util.List"%>
<%@page import="EntityClass.Employee"%>
<%@page import="EntityClass.Customer"%>
<%@page import="EntityClass.Product"%>
<%@page import="DAO.EmployeeDAO"%>
<%@page import="DAO.CustomerDAO"%>
<%@page import="DAO.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Getting All Employees For Select
    EmployeeDAO employeeDAO = new EmployeeDAO();
    List<Employee> empleados = employeeDAO.getAllEmployee();
    
   // Getting All Customers For Select
   CustomerDAO customerDAO = new CustomerDAO();
   List<Customer> clientes = customerDAO.getAllCustomer();
   
   // Getting All Product For Select
   ProductDAO productDAO = new ProductDAO();
   List<Product> productos = productDAO.getAllProducts();

%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registrar Ventas</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS Styles -->
        <link rel="stylesheet" href="../../css/style_pages/general.css">
        <link rel="stylesheet" href="../../css/components/navbar.css">
        <link rel="stylesheet" href="../../css/components/forms.css">
        <link rel="stylesheet" href="../../css/components/footer.css">
        <!-- Unicons CSS -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">
    </head>
    <body>
        <!-- NavBar -->
        <header class="navbar">
            <div>
                <a href="../../index.jsp"><img src="../../img/logo.webp" alt="logo" class="logo"></a>
            </div>
            <nav>
                <ul class="nav_list">
                    <li><a href="../company_info/about_us.jsp" class="link_ref">Sobre Nosotros</a></li>
                    <li><a href="../company_info/catalogue.jsp" class="link_ref">Catalogo</a></li>
                    <li><a href="../customer/index.jsp" class="link_ref">Clientes</a></li>
                    <li><a href="./index.jsp" class="link_ref">Ventas</a></li>
                </ul>
            </nav>
        </header>
        <!-- Info Message -->
        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
        <script>
            alert("<%= message %>");
        </script>
        <%
            session.removeAttribute("message");
            }  
        %>
        <!-- Main Content -->
        <main class="content_page">
            <div class="div_form">
                <!-- Header Form -->
                <div class="header_forms">
                    <h2 class="subtitle_page center_text">Registrar Venta</h2>
                    <p>Completa el Formulario Correctamente.</p>
                </div>
                <!-- Form Search -->
                <form action="/PatoVolador/SvSales" method="POST" class="form_style" enctype="multipart/form-data">
                    <!-- Servlet Action -->
                    <input type="hidden" name="action" value="create">
                    <!-- No. de Venta -->
                    <div class="form_group">
                        <label for="no_sale" class="form_label">Venta No.</label>
                        <div class="form_div_input">
                            <input type="text" name="no_sale" id="no_sale" class="form_input" value="Se Mostrara al Finalizar la Venta" disabled>
                        </div>
                    </div>
                    <!-- RFC Customer -->
                    <div class="form_group">
                        <label for="rfc_customer" class="form_label">RFC (Homoclave) - Cliente</label>
                        <div class="form_div_input">
                            <select name="rfc_customer" id="rfc_customer" class="form_input" required>
                                <option disabled selected hidden>Seleccione una opcion...</option>
                                <!-- List of Customers (RFC and Name)-->
                                <%
                                    for (Customer cust : clientes) { 
                                %>
                                    <option value="<%= cust.getRfc_customer() %>"><%= cust.getName_customer()%></option>
                                <% 
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <!-- RFC Employee -->
                    <div class="form_group">
                        <label for="rfc_employee" class="form_label">RFC (Homoclave) - Empleado</label>
                        <div class="form_div_input">
                            <select name="rfc_employee" id="rfc_employee" class="form_input" required>
                                <option disabled selected hidden>Seleccione una opcion...</option>
                                <!-- List of Employees (RFC and Name)-->
                                <%
                                    for (Employee emp : empleados) { 
                                %>
                                    <option value="<%= emp.getRfc_employee() %>"><%= emp.getName_employee() %></option>
                                <% 
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <!-- ID Product (ID Gun and Name) -->
                    <div class="form_group">
                        <label for="id_product" class="form_label">Nombre del Producto</label>
                        <div class="form_div_input">
                            <select name="id_product" id="id_product" class="form_input" required >
                                <option disabled selected hidden>Seleccione una opcion...</option>
                                <!-- Add List of Products (ID and Name)-->
                                <%
                                    for (Product pro : productos){
                                %>
                                <option value="<%= pro.getId_product() %>" data-precio="<%= pro.getPrice_product()%>" ><%= pro.getName_product() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <!-- Units -->
                    <div class="form_group">
                        <label for="units" class="form_label">Unidades</label>
                        <div class="form_div_input">
                            <select class="form_input" name="units" id="units" required>
                                <option disabled selected hidden>Seleccione una opcion...</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                    </div>
                    <!-- Unit Price -->
                    <div class="form_group">
                        <label for="unit_price" class="form_label">Precio Unitario</label>
                        <div class="form_div_input">
                            <input type="text" name="unit_price" id="unit_price" class="form_input" disabled>
                        </div>
                    </div>
                    <!-- Import Sale -->
                    <div class="form_group">
                        <label for="import_sale" class="form_label">Importe</label>
                        <div class="form_div_input">
                            <input type="text" name="import_sale" id="import_sale" class="form_input" disabled>
                        </div>
                    </div>
                    <!-- IVA Product -->
                    <div class="form_group">
                        <label for="iva_product" class="form_label">IVA (Producto)</label>
                        <div class="form_div_input">
                            <input type="text" name="iva_product" id="iva_product" class="form_input" disabled>
                        </div>
                    </div>
                    <!-- Subtotal -->
                    <div class="form_group">
                        <label for="subtotal" class="form_label">Subtotal</label>
                        <div class="form_div_input">
                            <input type="text" name="subtotal" id="subtotal" class="form_input" disabled>
                        </div>
                    </div>
                    <!-- IVA Total -->
                    <div class="form_group">
                        <label for="iva_total" class="form_label">IVA (Total)</label>
                        <div class="form_div_input">
                            <input type="text" name="iva_total" id="iva_total" class="form_input" disabled>
                        </div>
                    </div>
                    <!-- Total -->
                    <div class="form_group">
                        <label for="total" class="form_label">Total</label>
                        <div class="form_div_input">
                            <input type="text" name="total" id="total" class="form_input" disabled>
                        </div>
                    </div>
                    <!-- Digital Sign -->
                    <div class="form_group">
                        <label for="digital_sign" class="form_label">Firma Digital</label>
                        <div class="form_div_input">
                            <input type="file" name="digital_sign" id="digital_sign" class="form_input">
                        </div>
                    </div>
                    <!-- Public Key -->
                    <div class="form_group">
                        <label for="public_key" class="form_label">Llave Publica</label>
                        <div class="form_div_input">
                            <input type="file" name="public_key" id="public_key" class="form_input">
                        </div>
                    </div>
                    <!-- PPA -->
                    <div class="form_group">
                        <label for="ppa" class="form_label">PPA</label>
                        <div class="form_div_input">
                            <input type="file" name="ppa" id="ppa" class="form_input">
                        </div>
                    </div>
                    <div class="form_btns_send">
                        <button type="submit" class="form_btn">Registrar</button>
                        <button type="reset" class="form_btn">Limpiar</button>
                    </div>
                </form>
            </div>
        </main>
        <!-- Footer Page -->
        <footer>
            <p>El Pato Volador S.A. de C.V. - Mar de Java CECyT No. 9 Juan de Dios Batiz</p>
        </footer>
        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const productSelect = document.getElementById('id_product');
                const quantitySelect = document.getElementById('units');
                const unitPriceInput = document.getElementById('unit_price');
                const importProductInput = document.getElementById('import_sale');
                const ivaProductInput = document.getElementById('iva_product');
                const subtotalInput = document.getElementById('subtotal');
                const ivaTotalInput = document.getElementById('iva_total');
                const totalInput = document.getElementById('total');

                const IVA_PERCENTAGE = 0.16;  // IVA del 16%

                function updateAndCalculate() {
                    // Eliminar el atributo disabled de los inputs
                    unitPriceInput.removeAttribute('disabled');
                    importProductInput.removeAttribute('disabled');
                    ivaProductInput.removeAttribute('disabled');
                    subtotalInput.removeAttribute('disabled');
                    ivaTotalInput.removeAttribute('disabled');
                    totalInput.removeAttribute('disabled');

                    // Obtener precio unitario del producto seleccionado
                    const selectedOption = productSelect.options[productSelect.selectedIndex];
                    const unitPrice = parseFloat(selectedOption.getAttribute('data-precio')) || 0;
                    unitPriceInput.value = unitPrice.toFixed(2);

                    // Obtener la cantidad seleccionada
                    const quantity = parseInt(quantitySelect.value) || 0;

                    // Calcular el importe total del producto
                    const importAmount = unitPrice * quantity;
                    importProductInput.value = importAmount.toFixed(2);

                    // Calcular el IVA del producto
                    const productIVA = importAmount * IVA_PERCENTAGE;
                    ivaProductInput.value = productIVA.toFixed(2);

                    // Calcular el subtotal (importe total sin IVA)
                    subtotalInput.value = importAmount.toFixed(2);

                    // Calcular el IVA total (por ahora, igual al IVA del producto)
                    ivaTotalInput.value = productIVA.toFixed(2);

                    // Calcular el total (subtotal + IVA total)
                    const totalAmount = importAmount + productIVA;
                    totalInput.value = totalAmount.toFixed(2);
                }

                // Llamar a la función de cálculo al cambiar la selección del producto o la cantidad
                productSelect.addEventListener('change', updateAndCalculate);
                quantitySelect.addEventListener('change', updateAndCalculate);
            });
        </script>
    </body>
</html>
