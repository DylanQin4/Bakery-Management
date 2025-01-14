package itu.s5.bakery.p_conseille;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import itu.s5.bakery.produit.Produit;

@Entity
@Table(name = "p_conseilles")
public class PConseille {

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
}
