package itu.s5.bakery.produit.categorie;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    private final CategorieRepository repository;

    public CategorieService(CategorieRepository repository) {
        this.repository = repository;
    }

    public List<Categorie> getAllCategories() {
        return repository.findAll();
    }

    public Optional<Categorie> getCategorieById(Long id) {
        return repository.findById(id);
    }
}
