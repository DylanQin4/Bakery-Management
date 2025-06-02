package itu.s5.bakery.produit.historique;

import itu.s5.bakery.produit.Produit;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historique_prix")
public class HistoriquePrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Column(name = "ancien_prix_revient", nullable = false, precision = 15, scale = 2)
    private BigDecimal ancienPrixRevient;

    @Column(name = "ancien_prix_vente", nullable = false, precision = 15, scale = 2)
    private BigDecimal ancienPrixVente;

    @Column(name = "date_modification", nullable = false, updatable = false)
    private LocalDateTime dateModification;

    @PrePersist
    public void prePersist() {
        this.dateModification = LocalDateTime.now();
    }

    // Getters and setters
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

    public BigDecimal getAncienPrixRevient() {
        return ancienPrixRevient;
    }

    public void setAncienPrixRevient(BigDecimal ancienPrixRevient) {
        this.ancienPrixRevient = ancienPrixRevient;
    }

    public BigDecimal getAncienPrixVente() {
        return ancienPrixVente;
    }

    public void setAncienPrixVente(BigDecimal ancienPrixVente) {
        this.ancienPrixVente = ancienPrixVente;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
}
