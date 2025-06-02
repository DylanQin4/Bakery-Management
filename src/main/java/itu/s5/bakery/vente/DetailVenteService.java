package itu.s5.bakery.vente;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailVenteService {
    private final DetailVenteRepository detailVenteRepository;

    public DetailVenteService(DetailVenteRepository detailVenteRepository) {
        this.detailVenteRepository = detailVenteRepository;
    }

    public List<DetailVente> getDetailsByVente(Vente vente) {
        return detailVenteRepository.findByVente(vente);
    }

    public void saveDetailVente(DetailVente detailVente) {
        detailVenteRepository.save(detailVente);
    }
}
