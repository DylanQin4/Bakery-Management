package itu.s5.bakery.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepsitory extends JpaRepository<Ingredient, Long> {
}
