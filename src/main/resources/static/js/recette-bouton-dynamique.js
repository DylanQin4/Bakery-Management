function addIngredient() {
    const container = document.getElementById('ingredient-container');
    const rows = container.querySelectorAll('.ingredient-row');
    const index = rows.length; // Calculer l'index pour la nouvelle ligne

    // Créer une nouvelle ligne
    const row = document.createElement('div');
    row.classList.add('ingredient-row', 'flex', 'space-x-4', 'items-center', 'mb-4');
    row.innerHTML = `
        <select required name="recettes[${index}].ingredientId" class="block w-1/2">
            <option value="">-- Sélectionnez un ingrédient --</option>
            ${ingredients.map(ingredient => `
                <option value="${ingredient.id}">${ingredient.nom}</option>
            `).join('')}
        </select>
        <input type="number" required name="recettes[${index}].quantite" placeholder="Quantité" class="block w-1/2" step="0.1"/>
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
            if (select) select.name = `recette[${index}].ingredient.id`;
            if (input) input.name = `recette[${index}].quantite`;
        });
    } else {
        alert("Vous devez avoir au moins un ingrédient.");
    }
}
