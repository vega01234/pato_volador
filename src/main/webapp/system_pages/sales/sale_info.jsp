<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntityClass.Sale"%>
<%
    Sale sale = (Sale) session.getAttribute("sale");
    if (sale != null) {
    session.removeAttribute("sale");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Informacion Venta</title>
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
                    <h1 class="title_page center_text">Informacion de la Compra</h1>
                    <%
                        if (sale == null) {
                    %>
                    <p class="center_text"><b>ERROR: </b>No se encontró información del cliente.</p>
                    <%
                        } else {
                    %>
                    <p>Datos de la Compra No: <b><%= sale.getId_sale() %></b>.</p>
                </div>
                <!-- Data Customer -->
                <p><b>Fecha de la Venta: </b><%= sale.getDate_sale() %>.</p>
                <p><b>ID del Producto: </b><%= sale.getId_product() %>.</p>
                <p><b>Unidades Compradas: </b><%= sale.getUnits()%>.</p>
                <p><b>Subtotal de la Venta: </b><%= sale.getSubtotal_sale()%>.</p>
                <p><b>IVA Total de la Venta: </b><%= sale.getIva_sale()%>.</p>
                <p><b>Total de la Venta: </b><%= sale.getTotal_sale()%>.</p>
                <p><b>RFC del Empleado: </b><%= sale.getRfc_employee()%>.</p>
                <p><b>RFC del Cliente: </b><%= sale.getRfc_customer()%>.</p>
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
