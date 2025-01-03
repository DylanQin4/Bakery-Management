package itu.s5.bakery.recette;

import itu.s5.bakery.produit.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Long> {

    @Query("SELECT COUNT(p) FROM Produit p WHERE p NOT IN (SELECT DISTINCT r.produit FROM Recette r)")
    int countProduitSansRecette();

    @Query("SELECT r FROM Recette r WHERE r.produit = :produit")
    List<Recette> findByProduit(Produit produit);
}
