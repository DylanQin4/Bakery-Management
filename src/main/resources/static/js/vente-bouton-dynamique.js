function addProduit() {
    const container = document.getElementById('produit-container');
    const rows = container.querySelectorAll('.produit-row');
    const index = rows.length; // Déterminer l'index pour la nouvelle ligne

    // Créer une nouvelle ligne
    const row = document.createElement('div');
    row.classList.add('produit-row', 'flex', 'space-x-4', 'items-center', 'mb-4');
    row.innerHTML = `
        <select required
                name="detailVente[${index}].produitId"
                class="block w-1/2 rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white">
            <option value="" disabled selected>-- Sélectionnez un produit --</option>
            ${produits.map(produit => `
                <option value="${produit.id}">${produit.nom}</option>
            `).join('')}
        </select>
        <input type="number" name="detailVente[${index}].quantite" required step="1"
               placeholder="Quantité"
               class="block w-1/4 rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white" />
        <button type="button" onclick="removeProduit(this)" class="text-red-600 hover:underline">
            Supprimer
        </button>
    `;
    container.appendChild(row);
}

function removeProduit(button) {
    const row = button.closest('.produit-row');
    const container = document.getElementById('produit-container');

    if (container.children.length > 1) {
        container.removeChild(row);

        // Mettre à jour les indices des lignes restantes
        Array.from(container.children).forEach((child, index) => {
            const select = child.querySelector('select');
            const quantite = child.querySelector('input[name$="quantite"]');

            // Mettre à jour les attributs "name"
            if (select) select.name = `detailVente[${index}].produitId`;
            if (quantite) quantite.name = `detailVente[${index}].quantite`;
        });
    } else {
        alert("Vous devez avoir au moins un produit.");
    }
}