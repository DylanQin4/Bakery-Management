package itu.s5.bakery.recette;

import itu.s5.bakery.ingredient.Ingredient;
import itu.s5.bakery.produit.Produit;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "recette")
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La quantité est obligatoire")
    @DecimalMin(value = "0.01", message = "La quantité doit être positive")
    private float quantite;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    public Recette() {
    }

    public Recette(Long id, float quantite, Ingredient ingredient, Produit produit) {
        this.id = id;
        this.quantite = quantite;
        this.ingredient = ingredient;
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String toString() {
        return "Recette{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", ingredient=" + ingredient +
                ", produit=" + produit +
                '}';
    }
}
