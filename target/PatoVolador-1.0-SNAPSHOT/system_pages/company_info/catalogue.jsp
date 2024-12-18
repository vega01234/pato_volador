<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Catalogo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS Styles -->
        <link rel="stylesheet" href="../../css/style_pages/general.css">
        <link rel="stylesheet" href="../../css/components/navbar.css">
        <link rel="stylesheet" href="../../css/components/catalogue_cards.css">
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
                    <li><a href="./about_us.jsp" class="link_ref">Sobre Nosotros</a></li>
                    <li><a href="./catalogue.jsp" class="link_ref">Catalogo</a></li>
                    <li><a href="../customer/index.jsp" class="link_ref">Clientes</a></li>
                    <li><a href="../sales/index.jsp" class="link_ref">Ventas</a></li>
                </ul>
            </nav>
        </header>
        <!-- Main Content -->
        <main class="content_page">
            <h2 class="subtitle_page center_text">Catalogo de Armas</h2>
            <div class="catalogue_cards" id="catalogue_cards"></div>
        </main>
        <!-- Footer Page -->
        <footer>
            <p>El Pato Volador S.A. de C.V. - Mar de Java CECyT No. 9 Juan de Dios Batiz</p>
        </footer>
        <script src="../../scripts/utils/cardData.js"></script>
        <script src="../../scripts/renderCards.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function() {
                renderCatalogueCards(catalogueCards, "catalogue_cards");
            })
        </script>
    </body>
</html>
