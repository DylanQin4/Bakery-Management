package itu.s5.bakery.produit;

import itu.s5.bakery.recette.Recette;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nom;

    @NotNull
    @DecimalMin(value = "0.0001", message = "Le prix de revient doit être positif")
    private Double prixRevient;

    @NotNull
    @DecimalMin(value = "0.0001", message = "Le prix de vente doit être positif")
    private Double prixVente;

    @OneToMany(mappedBy = "produit")
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

    public Double getPrixRevient() {
        return prixRevient;
    }

    public void setPrixRevient(Double prixRevient) {
        this.prixRevient = prixRevient;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public List<Recette> getRecette() {
        return recette;
    }

    public void setRecette(List<Recette> recette) {
        this.recette = recette;
    }
}
