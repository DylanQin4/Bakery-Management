package itu.s5.bakery.vente;

import itu.s5.bakery.client.Client;
import itu.s5.bakery.client.ClientService;
import itu.s5.bakery.produit.Produit;
import itu.s5.bakery.produit.ProduitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VenteService {
    private final VenteRepository venteRepository;
    private final DetailVenteRepository detailVenteRepository;
    private final ClientService clientService;
    private final ProduitService produitService;

    public VenteService(VenteRepository venteRepository, DetailVenteRepository detailVenteRepository, ClientService clientService, ProduitService produitService) {
        this.venteRepository = venteRepository;
        this.detailVenteRepository = detailVenteRepository;
        this.clientService = clientService;
        this.produitService = produitService;
    }

    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    public Optional<Vente> getVenteById(Long id) {
        return venteRepository.findById(id);
    }

    @Transactional
    public void saveVente(VenteForm vente) {
        Vente newVente = new Vente();
        Optional<Client> client = clientService.getClientById(vente.getClientId());
        if (client.isEmpty()) {
            throw new RuntimeException("Client not found");
        }
        newVente.setClient(client.get());
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
