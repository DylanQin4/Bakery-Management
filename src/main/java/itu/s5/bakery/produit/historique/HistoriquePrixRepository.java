package itu.s5.bakery.produit.historique;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoriquePrixRepository extends JpaRepository<HistoriquePrix, Long> {
    @Query("SELECT hp FROM HistoriquePrix hp WHERE hp.produit.id = :produitId")
    List<HistoriquePrix> findAllByProduitId(Long produitId);
}
