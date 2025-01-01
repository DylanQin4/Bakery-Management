package itu.s5.bakery.service;

import itu.s5.bakery.model.Ingredients;
import itu.s5.bakery.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService {
    private final IngredientsRepository ingredientRepository;

    public IngredientsService(IngredientsRepository ingredientRepository){
        this.ingredientRepository=ingredientRepository;
    }
    public List<Ingredients> getAllIngredients(){
        return ingredientRepository.findAll();
    }
    public Ingredients saveIngredients(Ingredients ingredient){
        return ingredientRepository.save(ingredient);
    }
}  
