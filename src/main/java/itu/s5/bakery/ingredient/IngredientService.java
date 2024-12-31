package itu.s5.bakery.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepsitory ingredientRepsitory;

    @Autowired
    public IngredientService(IngredientRepsitory ingredientRepsitory) {
        this.ingredientRepsitory = ingredientRepsitory;
    }

    public List<Ingredient> getAllIngredient() {
        return ingredientRepsitory.findAll();
    }

    public Optional<Ingredient> getIngredientById(Long id) {
        return ingredientRepsitory.findById(id);
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepsitory.save(ingredient);
    }

    public void deleteIngredientById(Long id) {
        ingredientRepsitory.deleteById(id);
    }
}
