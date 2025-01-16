package itu.s5.bakery.produit_conseille;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import itu.s5.bakery.produit.ProduitService;

@Service
public class ProduitConseilleService {

    private final ProduitConseilleRepository ProduitConseilleRepository;

    @Autowired
    public ProduitConseilleService(ProduitConseilleRepository ProduitConseilleRepository) {
        this.ProduitConseilleRepository = ProduitConseilleRepository;
    }

    public List<ProduitConseille> getAllProduitConseilles() {
        return ProduitConseilleRepository.findAll();
    }

    public Optional<ProduitConseille> getProduitConseilleById(Long id) {
        return ProduitConseilleRepository.findById(id);
    }

    public ProduitConseille createProduitConseille(ProduitConseille ProduitConseille) {
        return ProduitConseilleRepository.save(ProduitConseille);
    }

    public void deleteProduitConseille(Long id) {
        ProduitConseille ProduitConseille = ProduitConseilleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProduitConseille non trouvé avec l'id : " + id));
        // Mettre à jour l'état au lieu de supprimer
        ProduitConseille.setEtat(0); // 0 ou toute autre valeur pour indiquer la suppression logique
        ProduitConseilleRepository.save(ProduitConseille);
    }
    public List<ProduitConseille> getActiveProduitConseilles() {
        return ProduitConseilleRepository.findByEtat(1); // 1 pour indiquer actif
    }


    public List<ProduitConseille> getPByMonth(Integer month, List<ProduitConseille> produitsConseilles) {
        return produitsConseilles.stream()
                .filter(pc -> pc.getDateConseillee() != null) // Vérifie que la date conseillée n'est pas nulle
                .filter(pc -> pc.getDateConseillee().getMonthValue() == month) // Filtre par mois
                .collect(Collectors.toList()); // Collecte les résultats
    }

    public List<ProduitConseille> getPByYear(Integer year, List<ProduitConseille> produitConseilles) {
        return produitConseilles.stream()
                .filter(pc -> pc.getDateConseillee() != null) // Vérifie que la date conseillée n'est pas nulle
                .filter(pc -> pc.getDateConseillee().getYear() == year) // Filtre par année
                .collect(Collectors.toList()); // Collecte les résultats
    }

//    public List<ProduitConseille> getPByMonth(Integer month) {
//        return ProduitConseilleRepository.findByMonth(month.intValue());
//    }

}
