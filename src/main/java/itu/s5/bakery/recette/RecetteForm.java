package itu.s5.bakery.recette;

import java.util.List;

public class RecetteForm {
    private Long produitId;
    private List<RecetteDTO> recettes;

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public List<RecetteDTO> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<RecetteDTO> recettes) {
        this.recettes = recettes;
    }
}
