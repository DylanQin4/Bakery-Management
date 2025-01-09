package itu.s5.bakery.produit;

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
@RequestMapping("/ressources/produits")
public class ProduitController {
    private final ProduitService service;

    @Autowired
    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllProduits(Model model, HttpServletRequest request) {
        List<Produit> produits = service.getAllProduits();
        model.addAttribute("produits", produits);
        return "produits/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpServletRequest request) {
        model.addAttribute("produit", new Produit());
        return "produits/form";
    }

    @PostMapping
    public String saveProduit(@Valid @ModelAttribute Produit produit, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "produits/form";
        }
        service.createProduit(produit);
        return "redirect:/ressources/produits";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        Optional<Produit> produit = service.getProduitById(id);
        if (produit.isEmpty()) {
            model.addAttribute("error", "Produit non trouve");
            return "redirect:/ressources/produits";
        }
        model.addAttribute("produit", produit.get());
        return "produits/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable Long id) {
        service.deleteProduit(id);
        return "redirect:/produits";
    }
}
