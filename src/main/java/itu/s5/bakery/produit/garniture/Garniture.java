package itu.s5.bakery.produit.garniture;

import com.fasterxml.jackson.annotation.JsonBackReference;
import itu.s5.bakery.produit.Produit;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "garnitures")
public class Garniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    @ManyToMany(mappedBy = "garnitures")
    @JsonBackReference
    private Set<Produit> produits;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}

