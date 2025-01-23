package itu.s5.bakery.vendeur;

import itu.s5.bakery.vente.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendeurService {
    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private VendeurRepository vendeurRepository;

    @Autowired
    private GenreRepository genreRepository;

    public BigDecimal getCommissionByVendeur(Long vendeurId, LocalDate startDate, LocalDate endDate) {
        BigDecimal totalVentes;

        // Conversion des LocalDate en LocalDateTime
        LocalDateTime startDateTime = (startDate != null) ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = (endDate != null) ? endDate.atTime(LocalTime.MAX) : null;

        // Vérifier les cas où les paramètres de date sont nuls
        if (startDateTime != null && endDateTime != null) {
            totalVentes = venteRepository.sumVentesByVendeurAndDateRange(vendeurId, startDateTime, endDateTime);
        } else if (startDateTime != null) {
            totalVentes = venteRepository.sumVentesByVendeurAndStartDate(vendeurId, startDateTime);
        } else if (endDateTime != null) {
            totalVentes = venteRepository.sumVentesByVendeurAndEndDate(vendeurId, endDateTime);
        } else {
            totalVentes = venteRepository.sumVentesByVendeur(vendeurId); // Cas où aucune date n'est spécifiée
        }

        // Gérer le cas où aucune vente n'est trouvée
        if (totalVentes == null) {
            return BigDecimal.ZERO;
        }

        // Calcul de la commission : 5%
        return totalVentes.multiply(BigDecimal.valueOf(0.05));
    }

    public List<CommissionGenre> getSumCommission(LocalDate stardDate, LocalDate endDate) {
        List<Genre> genres = genreRepository.findAll();
        List<CommissionGenre> resp = new ArrayList<>();
        for (Genre genre : genres) {
            if (stardDate == null || endDate == null) {
                resp.add(new CommissionGenre(genre, vendeurRepository.getSumCommissionByGenre(genre.getId())));
            } else {
                resp.add(new CommissionGenre(genre, vendeurRepository.getSumCommissionByGenre(genre.getId(), stardDate, endDate)));
            }
        }
        return resp;
    }

    public List<Vendeur> getAllVendeurs() {
        return vendeurRepository.findAll();
    }

    public Vendeur getVendeurById(Long id) {
        return vendeurRepository.getById(id);
    }
}
