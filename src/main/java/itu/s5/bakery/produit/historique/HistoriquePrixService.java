package itu.s5.bakery.produit.historique;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriquePrixService {

    private final HistoriquePrixRepository historiquePrixRepository;

    public HistoriquePrixService(HistoriquePrixRepository historiquePrixRepository) {
        this.historiquePrixRepository = historiquePrixRepository;
    }

    public List<HistoriquePrix> findAllByProduitId(Long produitId) {
        return historiquePrixRepository.findAllByProduitId(produitId);
    }

    public List<HistoriquePrix> findAll() {
        return historiquePrixRepository.findAll();
    }

    public HistoriquePrix save(HistoriquePrix historiquePrix) {
        return historiquePrixRepository.save(historiquePrix);
    }

    public void deleteById(Long id) {
        historiquePrixRepository.deleteById(id);
    }
}

