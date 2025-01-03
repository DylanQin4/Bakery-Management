function addIngredient() {
    const container = document.getElementById('ingredient-container');
    const rows = container.querySelectorAll('.ingredient-row');
    const index = rows.length; // Calculer l'index pour la nouvelle ligne

    // Créer une nouvelle ligne
    const row = document.createElement('div');
    row.classList.add('ingredient-row', 'flex', 'space-x-4', 'items-center', 'mb-4');
    row.innerHTML = `
        <select required
                name="recettes[${index}].ingredientId"
                class="block w-1/2 rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white">
            <option value="" disabled>-- Sélectionnez un ingrédient --</option>
            ${ingredients.map(ingredient => `
                <option value="${ingredient.id}">${ingredient.nom}</option>
            `).join('')}
        </select>
        <input type="number" name="recettes[${index}].quantite" required step="0.01"
               placeholder="Quantité"
               class="block w-1/2 rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white" />
        <button type="button" onclick="removeIngredient(this)" class="text-red-600 hover:underline">
            Supprimer
        </button>
    `;
    container.appendChild(row);
}

function removeIngredient(button) {
    const row = button.closest('.ingredient-row');
    const container = document.getElementById('ingredient-container');

    if (container.children.length > 1) {
        container.removeChild(row);

        // Mettre à jour les indices des lignes restantes
        Array.from(container.children).forEach((child, index) => {
            const select = child.querySelector('select');
            const input = child.querySelector('input');

            // Mettre à jour les attributs "name"
            if (select) select.name = `recettes[${index}].ingredientId`;
            if (input) input.name = `recettes[${index}].quantite`;
        });
    } else {
        alert("Vous devez avoir au moins un ingrédient.");
    }
}
