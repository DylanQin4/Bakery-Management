package itu.s5.bakery.Model;

import itu.s5.bakery.ingredient.Ingredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "details_achat")
public class DetailAchat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0.0001", message = "La quantité doit être positive")
    private Double quantite;

    @NotNull
    @DecimalMin(value = "0.0001", message = "Le prix unitaire doit être positif")
    private Double prixUnitaire;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_achat")
    private Achat achat;

    public DetailAchat() {
    }

    public DetailAchat(Long id, Double quantite, Double prixUnitaire, Ingredient ingredient, Achat achat) {
        this.id = id;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.ingredient = ingredient;
        this.achat = achat;
    }

    public Long getId() {
        return id;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Achat getAchat() {
        return achat;
    }

    public void setAchat(Achat achat) {
        this.achat = achat;
    }
}
