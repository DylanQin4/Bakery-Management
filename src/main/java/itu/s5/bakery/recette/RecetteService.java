package itu.s5.bakery.recette;


import itu.s5.bakery.ingredient.Ingredient;
import itu.s5.bakery.produit.Produit;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecetteService {
    private final RecetteRepository recetteRepository;

    public RecetteService(RecetteRepository recetteRepository) {
        this.recetteRepository = recetteRepository;
    }

    public int countProduitSansRecette() {
        return recetteRepository.countProduitSansRecette();
    }

    public List<RecetteDTO> getRecettesByProduit(Produit produit) {
        List<Recette> recettes = recetteRepository.findByProduit(produit);
        return recettes.stream()
                .map(r -> new RecetteDTO(produit.getId(), r.getIngredient().getId(), r.getQuantite()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveRecette(Produit produit, List<RecetteDTO> recetteDTOs) {
        List<Recette> existingRecettes = recetteRepository.findByProduit(produit);
        List<Recette> recettesToSave = new ArrayList<>();
        List<Recette> recettesToDelete = new ArrayList<>(existingRecettes);

        for (RecetteDTO dto : recetteDTOs) {
            if (dto.getQuantite() <= 0) {
                throw new IllegalArgumentException("La quantité doit être supérieure à 0");
            }

            Recette matchingRecette = null;
            for (Recette existingRecette : existingRecettes) {
                if (existingRecette.getIngredient().getId().equals(dto.getIngredientId())) {
                    matchingRecette = existingRecette;
                    break;
                }
            }

            if (matchingRecette != null) {
                matchingRecette.setQuantite(dto.getQuantite());
                recettesToSave.add(matchingRecette);
                recettesToDelete.remove(matchingRecette);
            } else {
                Recette newRecette = new Recette();
                newRecette.setProduit(produit);
                newRecette.setIngredient(new Ingredient(dto.getIngredientId()));
                newRecette.setQuantite(dto.getQuantite());
                recettesToSave.add(newRecette);
            }
        }

        System.out.println("Recettes à supprimer : " + recettesToDelete);

        if (!recettesToDelete.isEmpty()) {
            recetteRepository.deleteAll(recettesToDelete);
        }

        System.out.println("Recettes à sauvegarder : " + recettesToSave);

        if (!recettesToSave.isEmpty()) {
            recetteRepository.saveAll(recettesToSave);
        }
    }

    public RecetteForm getRecetteFormByProduit(Produit produit) {
        RecetteForm recetteForm = new RecetteForm();
        recetteForm.setProduitId(produit.getId());
        recetteForm.setRecettes(getRecettesByProduit(produit));
        return recetteForm;
    }

    public void deleteAllRecettesByProduit(Produit produit) {
        recetteRepository.deleteAll(recetteRepository.findByProduit(produit));
    }
}
