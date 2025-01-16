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
}
