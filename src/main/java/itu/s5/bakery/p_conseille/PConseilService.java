package itu.s5.bakery.produits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import itu.s5.bakery.produit.ProduitService;

@Service
public class PConseilleService {

    private final PConseilleRepository pConseilleRepository;

    @Autowired
    public PConseilleService(PConseilleRepository pConseilleRepository) {
        this.pConseilleRepository = pConseilleRepository;
    }

    public List<PConseille> getAllPConseilles() {
        return pConseilleRepository.findAll();
    }

    public Optional<PConseille> getPConseilleById(Long id) {
        return pConseilleRepository.findById(id);
    }

    public PConseille createPConseille(PConseille pConseille) {
        return pConseilleRepository.save(pConseille);
    }

    public void deletePConseille(Long id) {
        PConseille pConseille = pConseilleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("PConseille non trouvé avec l'id : " + id));
        // Mettre à jour l'état au lieu de supprimer
        pConseille.setEtat(0); // 0 ou toute autre valeur pour indiquer la suppression logique
        pConseilleRepository.save(pConseille);
    }
    public List<PConseille> getActivePConseilles() {
        return pConseilleRepository.findByEtat(1); // 1 pour indiquer actif
    }

}
