package itu.s5.bakery.vente;

import com.fasterxml.jackson.core.JsonProcessingException;
import itu.s5.bakery.client.ClientService;
import itu.s5.bakery.produit.ProduitService;
import itu.s5.bakery.produit.categorie.CategorieRepository;
import itu.s5.bakery.produit.categorie.CategorieService;
import itu.s5.bakery.produit.garniture.GarnitureService;
import itu.s5.bakery.vendeur.VendeurService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ventes")
public class VenteController {
    private final VenteService venteService;
    private final DetailVenteService detailVenteService;
    private final ClientService clientService;
    private final ProduitService produitService;
    private final CategorieService categorieService;
    private final GarnitureService garnitureService;
    private final VendeurService vendeurService;

    public VenteController(
            VenteService venteService,
            DetailVenteService detailVenteService,
            ClientService clientService,
            ProduitService produitService,
            CategorieService categorieService,
            GarnitureService garnitureService,
            VendeurService vendeurService) {
        this.venteService = venteService;
        this.detailVenteService = detailVenteService;
        this.clientService = clientService;
        this.produitService = produitService;
        this.categorieService = categorieService;
        this.garnitureService = garnitureService;
        this.vendeurService = vendeurService;
    }

    @GetMapping
    public String listVentes(
            @RequestParam(required = false) Long categorieId,
            @RequestParam(required = false) Long garnitureId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Long clientId,
            Model model
    ) {
        // Récupérer toutes les ventes avec les filtres possibles
        List<Vente> ventes = venteService.rechercherVentes(categorieId, garnitureId, date, clientId);

        // Ajouter les résultats au modèle
        model.addAttribute("ventes", ventes);
        model.addAttribute("categorieId", categorieId);
        model.addAttribute("garnitureId", garnitureId);
        model.addAttribute("date", date);
        model.addAttribute("clientId", clientId);

        // Ajouter les catégories, garnitures et clients pour les filtres
        model.addAttribute("categories", categorieService.getAllCategories());
        model.addAttribute("garnitures", garnitureService.getAllGarnitures());
        model.addAttribute("clients", clientService.getAllClient());

        return "ventes/list";
    }



    @GetMapping("/create")
    public String showCreateForm(Model model) throws JsonProcessingException {
        model.addAttribute("venteForm", new VenteForm());
        model.addAttribute("clients", clientService.getAllClient());
        model.addAttribute("produits", produitService.getAllProduits());
        model.addAttribute("vendeurs", vendeurService.getAllVendeurs());
        return "ventes/form";
    }

    @PostMapping
    public String saveVente(@Valid @ModelAttribute VenteForm venteForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("venteForm", venteForm);
            model.addAttribute("clients", clientService.getAllClient());
            model.addAttribute("produits", produitService.getAllProduits());
            model.addAttribute("error", "Erreur de validation:" + result.getAllErrors());
            return "ventes/form";
        }
        venteService.saveVente(venteForm);
        return "redirect:/ventes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) throws JsonProcessingException {
        Optional<Vente> vente = venteService.getVenteById(id);
        if (vente.isEmpty()) {
            model.addAttribute("error", "Vente non trouvée");
            return "redirect:/ventes";
        }
        model.addAttribute("venteForm", venteService.getVenteFormById(id));
        model.addAttribute("clients", clientService.getAllClient());
        model.addAttribute("produits", produitService.getAllProduits());
        return "ventes/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        Optional<Vente> vente = venteService.getVenteById(id);
        if (vente.isEmpty()) {
            model.addAttribute("error", "Vente non trouvée");
            return "redirect:/ventes";
        }
        model.addAttribute("vente", vente.get());
        model.addAttribute("details", detailVenteService.getDetailsByVente(vente.get()));
        return "ventes/details";
    }

    @GetMapping("/delete/{id}")
    public String deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
        return "redirect:/ventes";
    }
}
