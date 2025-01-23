package itu.s5.bakery.vendeur;

import itu.s5.bakery.client.Client;
import itu.s5.bakery.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("vendeurs")
public class VendeurController {
    @Autowired
    private VendeurService vendeurService;
    @Autowired
    private VendeurRepository vendeurRepository;

    @GetMapping
    public String getCommissions(
            @RequestParam(required = false) Long vendeurId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, Model model) {

        model.addAttribute("vendeurId", vendeurId);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        BigDecimal commission = vendeurService.getCommissionByVendeur(vendeurId, startDate, endDate);
        List<Vendeur> vendeurs = vendeurService.getAllVendeurs();
        model.addAttribute("vendeurs", vendeurs);

        List<VendeurResponse> vendeursR = new ArrayList<>();

        if (vendeurId == null) {
            for (int i = 0; i < vendeurs.size(); i++) {
                System.out.printf("commision " + i + " " + vendeurService.getCommissionByVendeur(vendeurs.get(i).getId(), startDate, endDate));
                VendeurResponse vendeur = new VendeurResponse(vendeurs.get(i), vendeurService.getCommissionByVendeur(vendeurs.get(i).getId(), startDate, endDate), startDate, endDate);
                vendeursR.add(vendeur);
            }
        } else {
            VendeurResponse vendeur = new VendeurResponse(vendeurService.getVendeurById(vendeurId), vendeurService.getCommissionByVendeur(vendeurId, startDate, endDate), startDate, endDate);
            vendeursR.add(vendeur);
        }

        model.addAttribute("vendeursR", vendeursR);

        return "vendeurs/list";
    }
}
