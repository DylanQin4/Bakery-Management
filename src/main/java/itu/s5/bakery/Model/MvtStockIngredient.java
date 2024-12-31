package itu.s5.bakery.Model;

import itu.s5.bakery.ingredient.Ingredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mvt_stock_ingredient")
public class MvtStockIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "ENTREE|SORTIE", message = "Le type de mouvement doit être 'ENTREE' ou 'SORTIE'")
    private String typeMvt;

    @NotNull
    @DecimalMin(value = "0.01", message = "La quantité doit être positive")
    private BigDecimal quantite;

    private LocalDateTime dateMvt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    public MvtStockIngredient() {
    }

    public MvtStockIngredient(Long id, String typeMvt, BigDecimal quantite, LocalDateTime dateMvt, Ingredient ingredient) {
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

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public LocalDateTime getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(LocalDateTime dateMvt) {
        this.dateMvt = dateMvt;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
