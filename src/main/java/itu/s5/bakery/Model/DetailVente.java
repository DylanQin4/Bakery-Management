package itu.s5.bakery.Model;

import itu.s5.bakery.produit.Produit;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "details_vente")
public class DetailVente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    private Integer quantite;

    @NotNull
    @DecimalMin(value = "0.01", message = "Le prix unitaire doit être positif")
    private Double prixUnitaire;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_vente")
    private Vente vente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    public DetailVente() {
    }

    public DetailVente(Long id, Integer quantite, Double prixUnitaire, Vente vente, Produit produit) {
        this.id = id;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.vente = vente;
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    // Getters and Setters
}
