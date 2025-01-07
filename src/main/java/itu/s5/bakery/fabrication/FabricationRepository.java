package itu.s5.bakery.fabrication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FabricationRepository extends JpaRepository<Fabrication, Long>{

   @Query(nativeQuery=true, value="select f.*
    FROM fabrication fabrication
    JOIN produits p ON f.id_produit=p.id
    JOIN recette r ON p.id=r.id_produit
    JOIN ingredients i ON r.id_ingredient=i.id
    WHERE i.nom ILIKE '%ingredient_name%';")
    List<Fabrication> findFabrication(String ingredient_name);
    
}