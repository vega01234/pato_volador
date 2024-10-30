// Values of Cards to Render
const cards = [
    {
        imgSrc: "../../img/customer/register_customer.webp",
        title: "Registrar Cliente",
        description: "Registrar un nuevo cliente.",
        linkHref: "./register_customer.html",
        linkText: "Registrar"
    },
    {
        imgSrc: "../../img/customer/modify_customer.webp",
        title: "Modificar Cliente",
        description: "Modificar a un cliente.",
        linkHref: "./search_customer_form.html",
        linkText: "Actualizar"
    },
    {
        imgSrc: "../../img/customer/search_customer.webp",
        title: "Buscar Cliente",
        description: "Mostrar datos de un cliente.",
        linkHref: "./search_customer_form.html",
        linkText: "Consultar"
    }
];

// Function to Load Cards in Customers Page
function renderCards() {
    const container = document.getElementById("card_container");

    cards.forEach(card => {
        const cardElement = document.createElement("div");
        cardElement.className = "vertical_card";
        cardElement.innerHTML = `
            <img src="${card.imgSrc}" alt="ImageCard">
            <div class="vertical_card_content">
                <h2>${card.title}</h2>
                <p>${card.description}</p>
            </div>
            <div class="vertical_card_button">
                <a href="${card.linkHref}">${card.linkText}</a>
            </div>
        `;
        container.appendChild(cardElement);
    });
}

// Render Cards on Load
document.addEventListener("DOMContentLoaded", renderCards);