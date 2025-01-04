package itu.s5.bakery.client;

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
@RequestMapping("/ressources/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService ){
        this.clientService=clientService;
    }

    @GetMapping
    public String getAllClient(Model model,HttpServletRequest request) {
       List<Client> client=clientService.getAllClient();
       model.addAttribute("currentUrl",request.getRequestURI());
       model.addAttribute("client",client);
       return "client/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpServletRequest request) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("client", new Client());
        return "client/form"; 
    }

    @PostMapping
    public String saveClient(@Valid @ModelAttribute Client client, BindingResult result, Model model,HttpServletRequest request){
        if(result.hasErrors()){
            model.addAttribute("currentUrl", request.getRequestURI());
            model.addAttribute("errors", result.getAllErrors());
            return "client/form";
        }
        clientService.createClient(client);
        return "redirect:/ressources/client";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        model.addAttribute("currentUrl", request.getRequestURI());
        Optional<Client> client = clientService.getClientById(id);
        if (client.isEmpty()) {
            model.addAttribute("error", "Client non trouvé");
            return "redirect:/ressources/client";
        }
        model.addAttribute("client", client.get());
        return "client/form"; // Vue pour le formulaire de modification
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return "redirect:/ressources/client";
    }
}
