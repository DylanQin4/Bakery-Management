package itu.s5.bakery.fabrication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FabricationRepository extends JpaRepository<Fabrication, Long>{

    @Query("SELECT f FROM Fabrication f " +
         "JOIN f.produit p " +
         "JOIN p.recette r " +
         "JOIN r.ingredient i " +
         "WHERE LOWER(i.nom) LIKE LOWER(CONCAT('%', :ingredientName, '%'))")
    List<Fabrication> findFabrication(@Param("ingredientName") String ingredientName);
    
}