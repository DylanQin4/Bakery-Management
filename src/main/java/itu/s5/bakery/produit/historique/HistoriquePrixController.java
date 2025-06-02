package itu.s5.bakery.produit.historique;

import itu.s5.bakery.produit.Produit;
import itu.s5.bakery.produit.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ressources/produits/historiques")
public class HistoriquePrixController {
    private final HistoriquePrixService historiquePrixService;
    private final ProduitService produitService;

    public HistoriquePrixController(HistoriquePrixService historiquePrixService, ProduitService produitService) {
        this.historiquePrixService = historiquePrixService;
        this.produitService = produitService;
    }

    @GetMapping
    public String getAll(
            Model model,
            @RequestParam(required = false) Long produitId
    ) {
        model.addAttribute("produitId", produitId);
        List<HistoriquePrix> historiques;

        if (produitId != null) {
            historiques = historiquePrixService.findAllByProduitId(produitId);
        } else {
            historiques = historiquePrixService.findAll();
        }

        List<Produit> produits = produitService.getAllProduits();
        model.addAttribute("historiques", historiques);
        model.addAttribute("produits", produits);
        return "produits/historique/list";
    }
}
