package itu.s5.bakery.vente;

import itu.s5.bakery.produit.Produit;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

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
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    @NotNull
    @DecimalMin(value = "0.01", message = "Le prix unitaire doit être positif")
    private BigDecimal prixUnitaire;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_vente")
    private Vente vente;

    public DetailVente() {
    }

    public DetailVente(Long id, Integer quantite, BigDecimal prixUnitaire, Vente vente, Produit produit) {
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

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
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

    public String toString() {
        return "DetailVente{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", prixUnitaire=" + prixUnitaire +
                ", vente=" + vente +
                ", produit=" + produit +
                '}';
    }

    // Getters and Setters
}
