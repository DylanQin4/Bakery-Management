package itu.s5.bakery.ingredient;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ressources/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final UniteService uniteService;

    @Autowired
    public IngredientController(IngredientService ingredientService,UniteService uniteService) {
        this.ingredientService = ingredientService;
        this.uniteService=uniteService;
    }

    @GetMapping
    public String getAllIngredients(Model model, HttpServletRequest request) {
        List<Ingredient> ingredients = ingredientService.getAllIngredient();
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("ingredients", ingredients);
        return "ingredients/list"; // Vue pour afficher les ingrédients
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpServletRequest request) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("unites", uniteService.getAllUnite());
        return "ingredients/form"; 
    }

    @PostMapping
    public String saveIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("currentUrl", request.getRequestURI());
            model.addAttribute("errors", result.getAllErrors());
            return "ingredients/form"; // Retour au formulaire en cas d'erreurs
        }
        ingredientService.createIngredient(ingredient);
        return "redirect:/ressources/ingredients"; // Rediriger vers la liste après création
    }

    // Afficher le formulaire de modification d'un ingrédient
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        model.addAttribute("currentUrl", request.getRequestURI());
        Optional<Ingredient> ingredient = ingredientService.getIngredientById(id);
        if (ingredient.isEmpty()) {
            model.addAttribute("error", "Ingrédient non trouvé");
            return "redirect:/ressources/ingredients";
        }
        model.addAttribute("ingredient", ingredient.get());
        model.addAttribute("unites", uniteService.getAllUnite());
        return "ingredients/form"; // Vue pour le formulaire de modification
    }

    // Supprimer un ingrédient
    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
        return "redirect:/ressources/ingredients";
    }
}
