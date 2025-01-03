package itu.s5.bakery.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public class ClientController {
    private final ClientService ClientService;

    @Autowired
    public ClientController(ClientService ClientService ){
        this.ClientService=ClientService;
    }

    @GetMapping
    public String getAllClient(Model model,HttpServletRequest request) {
       List<Client> client=ClientService.getAllClient();
       model.addAttribute("currentUrl",request.getRequestURI());
       model.addAttribute("Client",client);
       return "client/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpServletRequest request) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("Client", new Client());
        return "client/form"; 
    }

    @PostMapping
    public String saveClient(@Valid @ModelAttribute Client client, BindingResult result, Model model,HttpServletRequest request){
        if(result.hasErrors()){
            model.addAttribute("currentUrl", request.getRequestURI());
            model.addAttribute("errors", result.getAllErrors());
            return "client/form";
        }
        ClientService.createClient(client);
        return "redirect:/ressources/client";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        model.addAttribute("currentUrl", request.getRequestURI());
        Optional<Client> client = ClientService.getClientById(id);
        if (client.isEmpty()) {
            model.addAttribute("error", "Ingrédient non trouvé");
            return "redirect:/ressources/client";
        }
        model.addAttribute("client", client.get());
        return "client/form"; // Vue pour le formulaire de modification
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        ClientService.deleteClientById(id);
        return "redirect:/ressources/client";
    }
}
