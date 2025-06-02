package itu.s5.bakery.produit_conseille;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import itu.s5.bakery.produit.Produit;

@Entity
@Table(name = "p_conseilles")
public class ProduitConseille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit", nullable = false)
    private Produit produit;

    @Column(name = "date_conseillee", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateConseillee;

    @Column(nullable = true)
    private Integer etat = 1; // Par défaut, actif (1 = actif, 0 = supprimé)

    // Getters et Setters

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }
    // Getters et Setters


    public ProduitConseille() {
    }

    public ProduitConseille(Long id, Produit produit, LocalDateTime dateConseillee, Integer etat) {
        this.id = id;
        this.produit = produit;
        this.dateConseillee = dateConseillee;
        this.etat = etat;
    }

    public String getEtatString(){
        if (this.etat == 0) {
            return "Supprimer";
        }
        return "Active";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public LocalDateTime getDateConseillee() {
        return dateConseillee;
    }

    public void setDateConseillee(LocalDateTime dateConseillee) {
        this.dateConseillee = dateConseillee;
    }
}
