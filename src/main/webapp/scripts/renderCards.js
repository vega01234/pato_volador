function renderLinkCards(cards, containerID) {
    const container = document.getElementById(containerID);
    container.innerHTML = ""; // Clear Content Container

    cards.forEach(card => {
        const cardElement = document.createElement("div");
        cardElement.className = "vertical_card";
        cardElement.innerHTML = `
            <img src="${card.imgSrc}" alt="ImageCard">
            <div class="vertical_card_content">
                <h2>${card.title}</h2>
            </div>
            <div class="vertical_card_button">
                <a href="${card.linkHref}">${card.linkText}</a>
            </div>
        `;
        container.appendChild(cardElement);
    });
}

function renderCatalogueCards(cards, containerID) {
    const container = document.getElementById(containerID);
    container.innerHTML = ""; // Clear Content Container
    cards.forEach(card => {
        const cardElement = document.createElement("div");
        cardElement.className = "catalogue_card";
        cardElement.innerHTML = `
            <div class="catalogue_card_image">
                <img src="${card.imgSrc}" alt="ImageCard">
            </div>
            <div class="catalogue_card_content">
                <h2>${card.title}</h2>
                <p> $ ${card.price} USD</p>
            </div>
            <div class="catalogue_card_button">
                <a href="#">Comprar</a>
            </div>
        `;
        container.appendChild(cardElement);
    });
}