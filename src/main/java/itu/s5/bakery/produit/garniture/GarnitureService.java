package itu.s5.bakery.produit.garniture;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GarnitureService {
    private final GarnitureRepository repository;

    public GarnitureService(GarnitureRepository repository) {
        this.repository = repository;
    }

    public List<Garniture> getAllGarnitures() {
        return repository.findAll();
    }

    public Optional<Garniture> getGarnitureById(Long id) {
        return repository.findById(id);
    }
}
