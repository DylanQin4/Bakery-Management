package itu.s5.bakery.produit_conseille;

import itu.s5.bakery.produit.Produit;
import itu.s5.bakery.produit.ProduitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produit_conseille")
public class ProduitConseilleController {
    private final ProduitConseilleService service;
    private final ProduitService produitService;

    public ProduitConseilleController(ProduitConseilleService service, ProduitService produitService) {
        this.service = service;
        this.produitService = produitService;
    }
    @GetMapping
    public String getProduitConseile(Model model,

                                     @RequestParam(required = false) Integer month,
                                     @RequestParam(required = false) Integer year) {
        List<ProduitConseille> produitsConseilles = service.getAllProduitConseilles();

        if (month != null) {
            produitsConseilles = service.getPByMonth(month, produitsConseilles);
        }

        if (year != null) {
            produitsConseilles = service.getPByYear(year, produitsConseilles);
        }

        model.addAttribute("produitConseile", produitsConseilles);


        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "produit_conseille/list";
    }

    @GetMapping("/create")
    public String createProduitConseille(Model model) {
        model.addAttribute("produits", produitService.getAllProduits());
        model.addAttribute("produitConseille", new ProduitConseille());
        return "produit_conseille/form";
    }

    @PostMapping
    public String saveProduit(@Valid @ModelAttribute ProduitConseille produit, BindingResult result, Model model, HttpServletRequest request) {
        service.createProduitConseille(produit);
        return "redirect:/produit_conseille";
    }
}
