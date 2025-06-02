package itu.s5.bakery.fournisseur;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FournisseurService {
    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurService(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository=fournisseurRepository;
    }

    public List<Fournisseur> getAllFournisseur(){
        return fournisseurRepository.findAll();   
    }

    public Optional<Fournisseur> getFournisseurById(Long id){
        return fournisseurRepository.findById(id);
    }

    public Fournisseur createFournisseur(Fournisseur fournisseur){
        return fournisseurRepository.save(fournisseur);
    }

    public void deleteFournisseurById(Long Id){
        fournisseurRepository.deleteById(Id);
    }
}
