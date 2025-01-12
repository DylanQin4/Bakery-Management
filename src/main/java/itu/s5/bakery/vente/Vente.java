package itu.s5.bakery.vente;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private BigDecimal total;

    private LocalDateTime dateVente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL)
    private List<DetailVente> detailsVente;

    public Vente() {
    }

    public Vente(Long id, BigDecimal total, LocalDateTime dateVente, Client client) {
        this.id = id;
        this.total = total;
        this.dateVente = dateVente;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getDateVente() {
        return dateVente;
    }

    public void setDateVente(LocalDateTime dateVente) {
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

    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", total=" + total +
                ", dateVente=" + dateVente +
                ", client=" + client +
                ", detailsVente=" + detailsVente +
                '}';
    }
}
