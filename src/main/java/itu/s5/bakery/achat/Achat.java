package itu.s5.bakery.achat;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import itu.s5.bakery.fournisseur.Fournisseur;

@Entity
@Table(name = "achats")
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0.01", message = "Le total doit être positif")
    private Double total;

    @NotNull
    private Date dateAchat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "achat")
    private List<DetailAchat> detailsAchat;

    public Achat() {
    }

    public Achat(Long id, Double total, Date dateAchat, Fournisseur fournisseur) {
        this.id = id;
        this.total = total;
        this.dateAchat = dateAchat;
        this.fournisseur = fournisseur;
    }

    public Long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<DetailAchat> getDetailsAchat() {
        return detailsAchat;
    }

    public void setDetailsAchat(List<DetailAchat> detailsAchat) {
        this.detailsAchat = detailsAchat;
    }
}
