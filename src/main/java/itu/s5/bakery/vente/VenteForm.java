package itu.s5.bakery.vente;

import java.util.List;

public class VenteForm {
    private Long id;
    private Long clientId;
    private List<DetailVenteDTO> detailVente;
    private Long vendeurId;

    public VenteForm() {
    }

    public VenteForm(Long id, Long clientId, List<DetailVenteDTO> detailVente, Long vendeurId) {
        this.id = id;
        this.clientId = clientId;
        this.detailVente = detailVente;
        this.vendeurId = vendeurId;
    }

    public Long getVendeurId() {
        return vendeurId;
    }

    public void setVendeurId(Long vendeurId) {
        this.vendeurId = vendeurId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<DetailVenteDTO> getDetailVente() {
        return detailVente;
    }

    public void setDetailVente(List<DetailVenteDTO> detailVente) {
        this.detailVente = detailVente;
    }
}
