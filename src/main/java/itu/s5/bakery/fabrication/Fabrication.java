package itu.s5.bakery.fabrication;

import itu.s5.bakery.produit.Produit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "fabrication")
public class Fabrication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    private Integer quantite;

    private Date dateFabrication;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    public Fabrication() {
    }

    public Fabrication(Long id, Integer quantite, Date dateFabrication, Produit produit) {
        this.id = id;
        this.quantite = quantite;
        this.dateFabrication = dateFabrication;
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

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
