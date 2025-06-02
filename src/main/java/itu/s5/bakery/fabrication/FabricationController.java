package itu.s5.bakery.fabrication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import itu.s5.bakery.produit.ProduitService;


@Controller
@RequestMapping("/fabrications")
public class FabricationController {
    
    private final FabricationService fabricationService;
    private final ProduitService produitService;

    @Autowired
    public FabricationController(FabricationService fabricationService,ProduitService produitService){
        this.fabricationService=fabricationService;
        this.produitService=produitService;
    }

    @GetMapping({"", "/search"})
    public String getAllFabrication(
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "search", required = false, defaultValue = "") String search) {

        if (search.trim().isEmpty()) {
            model.addAttribute("fabrication", fabricationService.getAllFabrication());
        } else {
            List<Fabrication> fabrications = fabricationService.searchFabricationByIngredientName(search);
            model.addAttribute("fabrication", fabrications);
        }

        model.addAttribute("searchQuery", search);
        return "fabrication/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpServletRequest request) {
        model.addAttribute("fabrication", new Fabrication());
        model.addAttribute("produit", produitService.getAllProduits());
        return "fabrication/form"; 
    }

    @PostMapping
    public String saveFabrication(@Valid @ModelAttribute Fabrication fabrication, BindingResult result, Model model,HttpServletRequest request){
        if(result.hasErrors()){
            model.addAttribute("errors", result.getAllErrors());
            return "fabrication/form";
        }
        fabricationService.createFabrication(fabrication);
        return "redirect:/fabrications";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        Optional<Fabrication> fabrication = fabricationService.getFabricationById(id);
        if (fabrication.isEmpty()) {
            model.addAttribute("error", "Ingrédient non trouvé");
            return "redirect:/fabrications";
        }
        model.addAttribute("fabrication", fabrication.get());
        model.addAttribute("produit", produitService.getAllProduits());
        return "fabrication/form"; // Vue pour le formulaire de modification
    }

    @GetMapping("/delete/{id}")
    public String deletefabrication(@PathVariable Long id) {
        fabricationService.deleteFabricationById(id);
        return "redirect:/fabrications";
    }

    
}
