package itu.s5.bakery.Model;

import itu.s5.bakery.ingredient.Ingredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity
@Table(name = "mvt_stock_ingredient")
public class MvtStockIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "Entree|Sortie", message = "Le type de mouvement doit être 'Entree' ou 'Sortie'")
    private String typeMvt;

    @NotNull
    @DecimalMin(value = "0.01", message = "La quantité doit être positive")
    private Double quantite;

    private Date dateMvt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    public MvtStockIngredient() {
    }

    public MvtStockIngredient(Long id, String typeMvt, Double quantite, Date dateMvt, Ingredient ingredient) {
        this.id = id;
        this.typeMvt = typeMvt;
        this.quantite = quantite;
        this.dateMvt = dateMvt;
        this.ingredient = ingredient;
    }

    public Long getId() {
        return id;
    }

    public String getTypeMvt() {
        return typeMvt;
    }

    public void setTypeMvt(String typeMvt) {
        this.typeMvt = typeMvt;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Date getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
