package itu.s5.bakery.vente;

import itu.s5.bakery.client.Client;
import itu.s5.bakery.client.ClientService;
import itu.s5.bakery.produit.Produit;
import itu.s5.bakery.produit.ProduitService;
import itu.s5.bakery.vendeur.VendeurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenteService {
    private final VenteRepository venteRepository;
    private final ClientService clientService;
    private final ProduitService produitService;
    private final VendeurService vendeurService;

    public VenteService(VenteRepository venteRepository, ClientService clientService, ProduitService produitService, VendeurService vendeurService) {
        this.venteRepository = venteRepository;
        this.clientService = clientService;
        this.produitService = produitService;
        this.vendeurService = vendeurService;
    }

    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    public Optional<Vente> getVenteById(Long id) {
        return venteRepository.findById(id);
    }

    public List<Vente> rechercherVentes(Long categorieId, Long garnitureId, LocalDate date, Long clientId) {
        // Si tous les paramètres sont nuls, renvoyer toutes les ventes
        if (categorieId == null && garnitureId == null && date == null && clientId == null) {
            return venteRepository.findAll();
        }
        System.out.println("date = " + date);

        List<Vente> ventes;

        // Si la catégorie et la garniture sont nulles, récupérer toutes les ventes
        if (categorieId == null && garnitureId == null) {
            ventes = venteRepository.findAll(); // Ou une autre méthode pour récupérer toutes les ventes
        } else {
            // Sinon, filtrer par catégorie ou garniture
            ventes = venteRepository.findVentesByCategorieOrGarniture(categorieId, garnitureId);
        }

        if (clientId != null) {
            // Filtrage des ventes selon le client
            ventes = ventes.stream()
                    .filter(vente -> vente.getClient().getId().equals(clientId))
                    .collect(Collectors.toList());
        }

        // Filtrage des ventes selon la date (si elle est spécifiée)
        return ventes.stream()
                .filter(vente -> (date == null || vente.getDateVente().toLocalDate().equals(date))) // Filtre par date
                .collect(Collectors.toList());
    }




    @Transactional
    public void saveVente(VenteForm vente) {
        Vente newVente = new Vente();
        Optional<Client> client = clientService.getClientById(vente.getClientId());
        if (client.isEmpty()) {
            throw new RuntimeException("Client not found");
        }
        newVente.setClient(client.get());
        newVente.setVendeur(vendeurService.getVendeurById(vente.getVendeurId()));
        List<DetailVente> detailVentes = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (DetailVenteDTO detail : vente.getDetailVente()) {
            Produit produit = produitService.getProduitById(detail.getProduitId()).orElseThrow();
            DetailVente newDetailVente = new DetailVente();
            newDetailVente.setProduit(produit);
            newDetailVente.setQuantite(detail.getQuantite());
            newDetailVente.setVente(newVente);
            newDetailVente.setPrixUnitaire(produit.getPrixVente());
            detailVentes.add(newDetailVente);
            total = total.add(newDetailVente.getProduit().getPrixVente().multiply(new BigDecimal(newDetailVente.getQuantite())));
        }
        newVente.setDetailsVente(detailVentes);
        newVente.setTotal(total);
        newVente.setDateVente(LocalDateTime.now());

        venteRepository.save(newVente);
    }

    public VenteForm getVenteFormById(Long id) {
        Optional<Vente> vente = venteRepository.findById(id);
        if (vente.isEmpty()) {
            throw new RuntimeException("Vente not found");
        }
        VenteForm venteForm = new VenteForm();
        venteForm.setId(vente.get().getId());
        venteForm.setClientId(vente.get().getClient().getId());
        List<DetailVenteDTO> detailVente = new ArrayList<>();
        for (DetailVente detail : vente.get().getDetailsVente()) {
            DetailVenteDTO detailVenteDTO = new DetailVenteDTO();
            detailVenteDTO.setProduitId(detail.getProduit().getId());
            detailVenteDTO.setQuantite(detail.getQuantite());
            detailVente.add(detailVenteDTO);
        }
        venteForm.setDetailVente(detailVente);
        return venteForm;
    }

    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }
}
