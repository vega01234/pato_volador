<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntityClass.Customer"%>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer != null) {
    session.removeAttribute("customer");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Informacion del Cliente</title>
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
        <!-- Main Content -->
        <main class="content_page">
            <!-- Customer Info -->
            <div class="div_form">
                <!-- Header Info -->
                <div class="header_forms">
                    <h1 class="title_page center_text">Informacion del Cliente</h1>
                    <%
                        if (customer == null) {
                    %>
                    <p class="center_text"><b>ERROR: </b>No se encontró información del cliente.</p>
                    <%
                        } else {
                    %>
                    <p>Datos del cliente con RFC: <b><%= customer.getRfc_customer() %></b>.</p>
                </div>
                <!-- Data Customer -->
                <p><b>ID del Usuario: </b><%= customer.getId_user()%>.</p>
                <p><b>Nombre del Cliente: </b><%= customer.getName_customer() %>.</p>
                <p><b>CURP del Cliente: </b><%= customer.getCurp_customer() %>.</p>
                <p><b>Fecha de Nacimiento: </b><%= customer.getDate_birth_customer() %>.</p>
                <p><b>Direccion del Cliente: </b><%= customer.getAdress_customer() %>.</p>
                <p><b>Estado Civil del Cliente: </b><%= customer.getCivil_state_customer() %>.</p>
                <p><b>Profesion del Cliente: </b><%= customer.getProfession_customer() %>.</p>
                <p><b>Grado de Estudios del Cliente: </b><%= customer.getDegree_study_customer() %>.</p>
            </div>
            <%
                }
            %>
        </main>
        <!-- Footer Page -->
        <footer>
            <p>El Pato Volador S.A. de C.V. - Mar de Java CECyT No. 9 Juan de Dios Batiz</p>
        </footer>
    </body>
</html>
