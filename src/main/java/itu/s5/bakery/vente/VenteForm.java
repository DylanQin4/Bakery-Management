package itu.s5.bakery.vente;

import java.util.List;

public class VenteForm {
    private Long id;
    private Long clientId;
    private List<DetailVenteDTO> detailVente;

    public VenteForm() {
    }

    public VenteForm(Long id, Long clientId, List<DetailVenteDTO> detailVente) {
        this.id = id;
        this.clientId = clientId;
        this.detailVente = detailVente;
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
