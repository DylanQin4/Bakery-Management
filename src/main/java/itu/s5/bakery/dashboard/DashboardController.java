package itu.s5.bakery.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping
    public String bashboard() {
        return "redirect:/ressources/produits";
    }
}
