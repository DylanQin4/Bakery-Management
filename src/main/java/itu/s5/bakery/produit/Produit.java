package itu.s5.bakery.produit;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import itu.s5.bakery.recette.Recette;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Le nom ne doit pas contenir de caractères spéciaux")
    @Size(max = 200)
    private String nom;

    @NotNull(message = "Le prix de revient est obligatoire")
    @DecimalMin(value = "0.0001", message = "Le prix de revient doit être positif")
    private BigDecimal prixRevient;

    @NotNull(message = "Le prix de vente est obligatoire")
    @DecimalMin(value = "0.0001", message = "Le prix de vente doit être positif")
    private BigDecimal prixVente;

    @OneToMany(mappedBy = "produit")
    @JsonManagedReference
    private List<Recette> recette;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrixRevient() {
        return prixRevient;
    }

    public void setPrixRevient(BigDecimal prixRevient) {
        this.prixRevient = prixRevient;
    }

    public BigDecimal getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(BigDecimal prixVente) {
        this.prixVente = prixVente;
    }

    public List<Recette> getRecette() {
        return recette;
    }

    public void setRecette(List<Recette> recette) {
        this.recette = recette;
    }
}
