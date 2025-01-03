package itu.s5.bakery.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import itu.s5.bakery.client.Client;

@Entity
@Table(name = "ventes")
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0.01", message = "Le total doit être positif")
    private Double total;

    @NotNull
    private Date dateVente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "vente")
    private List<DetailVente> detailsVente;

    public Vente() {
    }

    public Vente(Long id, Double total, Date dateVente, Client client) {
        this.id = id;
        this.total = total;
        this.dateVente = dateVente;
        this.client = client;
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

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<DetailVente> getDetailsVente() {
        return detailsVente;
    }

    public void setDetailsVente(List<DetailVente> detailsVente) {
        this.detailsVente = detailsVente;
    }
}
