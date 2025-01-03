package itu.s5.bakery.produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    @Query("SELECT p FROM Produit p WHERE p.id NOT IN (SELECT r.produit.id FROM Recette r)")
    public List<Produit> getProduitsWithoutRecette();
}
