package itu.s5.bakery.fournisseur;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ressources/fournisseur")
public class FournisseurController {
    
    private final FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }
    
    @GetMapping
    public String getAllFournisseur(Model model,HttpServletRequest request){
        List<Fournisseur> fournisseur=fournisseurService.getAllFournisseur();
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpServletRequest request) {
        model.addAttribute("fournisseur", new Fournisseur());
        return "fournisseur/form";
    }

    @PostMapping
    public String saveFournisseur(@Valid @ModelAttribute Fournisseur fournisseur,BindingResult result, Model model,HttpServletRequest request){
        if(result.hasErrors()){
            model.addAttribute("errors", result.getAllErrors());
            return "fournisseur/form";
        }
        fournisseurService.createFournisseur(fournisseur);
        return "redirect:/ressources/fournisseur";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        Optional<Fournisseur>fournisseur=fournisseurService.getFournisseurById(id);
        if(fournisseur.isEmpty()){
            model.addAttribute("error", "Fournisseur non trouvé");
            return "redirect:/ressources/fournisseur";
        }
        model.addAttribute("fournisseur", fournisseur.get());
        return "fournisseur/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFournisseur(@PathVariable Long id){
        fournisseurService.deleteFournisseurById(id);
        return "redirect:/ressources/fournisseur";
    }
}
