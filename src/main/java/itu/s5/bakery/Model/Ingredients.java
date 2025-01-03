package itu.s5.bakery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Double besoinNormal;
    private Double besoinFerie;

    public Double getBesoinNormal() {
        return besoinNormal;
    }
    public void setBesoinNormal(Double besoinNormal) {
        this.besoinNormal = besoinNormal;
    }

    public Double getBesoinFerie() {
        return besoinFerie;
    }
    public void setBesoinFerie(Double besoinFerie) {
        this.besoinFerie = besoinFerie;
    }
    public Long getId() { return id;}
    public void setId(Long id) { this.id=id;}
    public  String getNom() {return nom;}
    public void setNom(String nom) {this.nom=nom;}
}
