package itu.s5.bakery.ingredient;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    private String nom;

    @NotNull
    @DecimalMin(value = "0.01", message = "Le besoin normal doit être positif")
    private Double besoinNormal;

    @NotNull
    @DecimalMin(value = "0.01", message = "Le besoin férié doit être positif")
    private Double besoinFerie;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_unite")
    private Unite unite;

    public Ingredient(Long id, String nom, Double besoinNormal, Double besoinFerie, Unite unite) {
        this.id = id;
        this.nom = nom;
        this.besoinNormal = besoinNormal;
        this.besoinFerie = besoinFerie;
        this.unite = unite;
    }

    public Ingredient(Long id) {
        this.id = id;
    }

    public Ingredient() {
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

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }
}
