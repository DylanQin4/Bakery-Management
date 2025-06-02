package itu.s5.bakery.recette;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class RecetteDTO {
    private Long produitId;

    @NotNull(message = "L'identifiant de l'ingrédient est obligatoire.")
    private Long ingredientId;

    @DecimalMin(value = "0.0001", message = "La quantité doit être supérieure à zéro.")
    private float quantite;

    public RecetteDTO() {
    }

    public RecetteDTO(Long produitId, Long ingredientId, float quantite) {
        this.produitId = produitId;
        this.ingredientId = ingredientId;
        this.quantite = quantite;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }
}
