<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registrar Nuevo Cliente</title>
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
                    <li><a href="../sales/index.jsp" class="link_ref">Ventas</a></li>
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
                    <h2 class="subtitle_page center_text">Registrar Nuevo Cliente</h2>
                    <p>Completa el Formulario Correctamente.</p>
                </div>
                <!-- Form Search -->
                <form action="/PatoVolador/SvCustomer" method="POST" class="form_style" enctype="multipart/form-data">
                    <!-- Servlet Action -->
                    <input type="hidden" name="action" value="create">
                    <!-- Full Name -->
                    <div class="form_group">
                        <label for="full_name" class="form_label">Nombre Completo</label>
                        <div class="form_div_input">
                            <input type="text" name="full_name" id="full_name" class="form_input" required>
                        </div>
                    </div>
                    <!-- CURP Customer -->
                    <div class="form_group">
                        <label for="curp" class="form_label">CURP</label>
                        <div class="form_div_input">
                            <input type="text" name="curp" id="curp" class="form_input" required>
                        </div>
                    </div>
                    <!-- RFC Customer -->
                    <div class="form_group">
                        <label for="rfc" class="form_label">RFC (Homoclave)</label>
                        <div class="form_div_input">
                            <input type="text" name="rfc" id="rfc" class="form_input" required>
                        </div>
                    </div>
                    <!-- Birth Date -->
                    <div class="form_group">
                        <label for="birth_date" class="form_label">Fecha de Nacimiento</label>
                        <div class="form_div_input">
                            <input type="date" name="birth_date" id="birth_date" class="form_input" required>
                        </div>
                    </div>
                    <!-- Nacionality -->
                    <div class="form_group">
                        <label for="nacionality" class="form_label">Nacionalidad</label>
                        <div class="form_div_input">
                            <input type="text" name="nacionality" id="nacionality" class="form_input" required>
                        </div>
                    </div>
                    <!-- Adress -->
                    <div class="form_group">
                        <label for="adress" class="form_label">Domicilio</label>
                        <div class="form_div_input">
                            <input type="text" name="adress" id="adress" class="form_input" required>
                        </div>
                    </div>
                    <!-- Civil State -->
                    <div class="form_group">
                        <label for="civil_state" class="form_label">Estado Civil</label>
                        <div class="form_div_input">
                            <input type="text" name="civil_state" id="civil_state" class="form_input" required>
                        </div>
                    </div>
                    <!-- Ocupation -->
                    <div class="form_group">
                        <label for="ocupation" class="form_label">Ocupacion</label>
                        <div class="form_div_input">
                            <input type="text" name="ocupation" id="ocupation" class="form_input" required>
                        </div>
                    </div>
                    <!-- Study Degree -->
                    <div class="form_group">
                        <label for="study_degree" class="form_label">Grado de Estudios</label>
                        <div class="form_div_input">
                            <input type="text" name="study_degree" id="study_degree" class="form_input" required>
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
                    <!-- Form Buttons -->
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
    </body>
</html>
