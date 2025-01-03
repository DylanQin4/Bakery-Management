package itu.s5.bakery.client;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String nom;

    @NotBlank
    @Pattern(regexp = "PROFESSIONNEL|PARTICULIER", message = "Le type doit être soit 'PROFESSIONNEL' soit 'PARTICULIER'")
    private String type;

    public Client() {
    }

    public Client(Long id, String nom, String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
