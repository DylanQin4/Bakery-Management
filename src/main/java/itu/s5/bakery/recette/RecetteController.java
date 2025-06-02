package itu.s5.bakery.recette;

import itu.s5.bakery.ingredient.IngredientService;
import itu.s5.bakery.produit.Produit;
import itu.s5.bakery.produit.ProduitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ressources/recettes")
public class RecetteController {
    private final RecetteService recetteService;
    private final ProduitService produitService;
    private final IngredientService ingredientService;

    public RecetteController(RecetteService recetteService, ProduitService produitService, IngredientService ingredientService) {
        this.recetteService = recetteService;
        this.produitService = produitService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("produitsSansRecette", produitService.getProduitsSansRecette());
        model.addAttribute("produits", produitService.getAllProduits());
        return "recettes/list";
    }

    @PostMapping
    public String saveRecette(@ModelAttribute RecetteForm recetteForm, HttpServletRequest request) {
        Produit produit = produitService.getProduitById(recetteForm.getProduitId())
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));

        recetteService.saveRecette(produit, recetteForm.getRecettes());
        return "redirect:/ressources/recettes";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("recetteForm", new RecetteForm());
        model.addAttribute("produits", produitService.getProduitsSansRecette());
        model.addAttribute("ingredients", ingredientService.getAllIngredient());
        return "recettes/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Produit> produit = produitService.getProduitById(id);
        if (produit.isEmpty()) {
            model.addAttribute("error", "Recette non trouvée");
            return "redirect:/ressources/recettes";
        }
        model.addAttribute("recetteForm", recetteService.getRecetteFormByProduit(produit.get()));
        model.addAttribute("produits", List.of(produit.get()));
        model.addAttribute("ingredients", ingredientService.getAllIngredient());
        return "recettes/form";
    }

    @GetMapping("/delete/{produitId}")
    public String deleteRecette(@PathVariable Long produitId) {
        Optional<Produit> produit = produitService.getProduitById(produitId);
        if (produit.isEmpty()) {
            return "redirect:/ressources/recettes";
        }
        recetteService.deleteAllRecettesByProduit(produit.get());
        return "redirect:/ressources/recettes";
    }
}
