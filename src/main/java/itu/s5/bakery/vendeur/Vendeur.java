package itu.s5.bakery.vendeur;


import itu.s5.bakery.vente.Vente;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vendeur")
public class Vendeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column
    private int commission;

    @OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL)
    private List<Vente> ventes;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Vendeur(Long id, String nom, int commission, List<Vente> ventes, Genre genre) {
        this.id = id;
        this.nom = nom;
        this.commission = commission;
        this.ventes = ventes;
        this.genre = genre;
    }

    public Vendeur() {
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

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

    public List<Vente> getVentes() {
        return ventes;
    }

    public void setVentes(List<Vente> ventes) {
        this.ventes = ventes;
    }
}

