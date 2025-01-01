package itu.s5.bakery.repository;

import itu.s5.bakery.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients,Long>{
    
}
