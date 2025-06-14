package itu.s5.bakery.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniteService {
    private final UniteRepository uniteRepository;

    @Autowired
    public UniteService(UniteRepository uniteRepository) {
        this.uniteRepository = uniteRepository;
    }

    public Unite createUnite(Unite unite) {
        return uniteRepository.save(unite);
    }
    public List<Unite> getAllUnite() {
        return uniteRepository.findAll();
    }
}
