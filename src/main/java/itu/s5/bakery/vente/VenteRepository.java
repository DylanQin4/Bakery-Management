package itu.s5.bakery.vente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, Long> {

    @Query(value = """
        SELECT v.*,
               ARRAY_AGG(DISTINCT c.id) AS categories,
               ARRAY_AGG(DISTINCT g.id) AS garnitures
        FROM ventes v
        JOIN details_vente dv ON v.id = dv.id_vente
        JOIN produits p ON dv.id_produit = p.id
        JOIN produits_categories pc ON p.id = pc.id_produit
        JOIN categories c ON pc.id_categorie = c.id
        JOIN produits_garnitures pg ON p.id = pg.id_produit
        JOIN garnitures g ON pg.id_garniture = g.id
        GROUP BY v.id, v.date_vente, v.total
        HAVING :idCategorie = ANY(ARRAY_AGG(DISTINCT c.id)) OR :idGarniture = ANY(ARRAY_AGG(DISTINCT g.id))
        """, nativeQuery = true)
    List<Vente> findVentesByCategorieOrGarniture(
            @Param("idCategorie") Long idCategorie,
            @Param("idGarniture") Long idGarniture
    );

    @Query("SELECT SUM(v.total) FROM Vente v WHERE v.vendeur.id = :vendeurId AND v.dateVente BETWEEN :startDate AND :endDate")
    BigDecimal sumVentesByVendeurAndDateRange(@Param("vendeurId") Long vendeurId,
                                              @Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate);



    @Query("SELECT SUM(v.total) FROM Vente v WHERE v.vendeur.id = :vendeurId AND v.dateVente >= :startDate")
    BigDecimal sumVentesByVendeurAndStartDate(@Param("vendeurId") Long vendeurId,
                                              @Param("startDate") LocalDateTime startDate);

    @Query("SELECT SUM(v.total) FROM Vente v WHERE v.vendeur.id = :vendeurId AND v.dateVente <= :endDate")
    BigDecimal sumVentesByVendeurAndEndDate(@Param("vendeurId") Long vendeurId,
                                              @Param("endDate") LocalDateTime endDate);


    @Query("SELECT SUM(v.total) FROM Vente v WHERE v.vendeur.id = :vendeurId")
    BigDecimal sumVentesByVendeur(@Param("vendeurId") Long vendeurId);


}
