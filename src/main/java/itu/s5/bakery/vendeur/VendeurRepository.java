package itu.s5.bakery.vendeur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface VendeurRepository extends JpaRepository<Vendeur, Long> {

    @Query(value = "SELECT SUM(v.total * 0.05) FROM vendeur JOIN ventes v on vendeur.id = v.id_vendeur WHERE vendeur.id_genre = :genreId AND v.total >= 10 AND date_vente BETWEEN :startDate AND :endDate", nativeQuery = true)
    BigDecimal getSumCommissionByGenre(@Param(value = "genreId") Integer genreId,@Param(value = "startDate") LocalDate startDate,@Param(value = "endDate") LocalDate endDate);

    @Query(value = "SELECT SUM(v.total * 0.05) FROM vendeur JOIN ventes v on vendeur.id = v.id_vendeur WHERE vendeur.id_genre = :genreId AND v.total >= 10", nativeQuery = true)
    BigDecimal getSumCommissionByGenre(@Param(value = "genreId") Integer genreId);
}
