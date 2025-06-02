package itu.s5.bakery.vente;

public class DetailVenteDTO {
    private Long produitId;
    private int quantite;

    public DetailVenteDTO() {
    }

    public DetailVenteDTO(Long produitId, int quantite) {
        this.produitId = produitId;
        this.quantite = quantite;
    }

    public Long getProduitId() {
        return produitId;
    }
    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
