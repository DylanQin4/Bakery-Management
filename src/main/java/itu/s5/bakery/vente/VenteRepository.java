package itu.s5.bakery.vente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, Long> {
    @Query(value = """
        SELECT *
        FROM v_ventes_par_categorie_garniture
        WHERE :idCategorie = ANY(categories) AND :idGarniture = ANY(garnitures)
        """, nativeQuery = true)
    List<Vente> findVentesByCategorieAndGarniture(
            @Param("idCategorie") Long idCategorie,
            @Param("idGarniture") Long idGarniture
    );

    @Query(value = """
        SELECT *
        FROM v_ventes_par_categorie_garniture
        WHERE :idCategorie = ANY(categories) OR :idGarniture = ANY(garnitures)
        """, nativeQuery = true)
    List<Vente> findVentesByCategorieOrGarniture(
            @Param("idCategorie") Long idCategorie,
            @Param("idGarniture") Long idGarniture
    );
}
