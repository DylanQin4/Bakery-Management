package itu.s5.bakery.produit_conseille;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitConseilleRepository extends JpaRepository<ProduitConseille, Long> {
    // Méthodes spécifiques si nécessaire
    List<ProduitConseille> findByEtat(Integer etat);

//    @Query("SELECT p FROM ProduitConseille p WHERE FUNCTION('EXTRACT', 'MONTH', p.dateConseillee) = :month")
//    List<ProduitConseille> findByMonth(@Param("month") int month);
}

