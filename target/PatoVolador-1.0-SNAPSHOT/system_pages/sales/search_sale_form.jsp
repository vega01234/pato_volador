<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Buscar Venta</title>
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
            <div class="div_form">
                <!-- Header Form -->
                <div class="header_forms">
                    <h2 class="subtitle_page center_text">Buscar Venta</h2>
                    <p>Completa el Formulario Correctamente.</p>
                </div>
                <!-- Form Search -->
                <form action="/PatoVolador/SvSales" method="POST" class="form_style">
                    <!-- Servlet Action -->
                    <input type="hidden" name="action" value="search">
                    <!-- No. de Venta -->
                    <div class="form_group">
                        <label for="no_sale" class="form_label">Numero de Venta</label>
                        <div class="form_div_input">
                            <input type="text" name="no_sale" id="no_sale" class="form_input">
                        </div>
                    </div>
                    <div class="form_btns_send">
                        <button type="submit" class="form_btn">Buscar</button>
                        <button type="reset" class="form_btn">Limpiar</button>
                    </div>
                </form>
            </div>
        </main>
        <!-- Footer Page -->
        <footer>
            <p>El Pato Volador S.A. de C.V. - Mar de Java CECyT No. 9 Juan de Dios Batiz</p>
        </footer>
    </body>
</html>
