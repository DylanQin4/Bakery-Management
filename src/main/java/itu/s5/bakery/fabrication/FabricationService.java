package itu.s5.bakery.fabrication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FabricationService {
    private final FabricationRepository fabricationRepository;

    @Autowired
    public FabricationService(FabricationRepository fabricationRepository){
        this.fabricationRepository=fabricationRepository;
    }

    public List<Fabrication> getAllFabrication(){
        return fabricationRepository.findAll();   
    }

    public Optional<Fabrication> getFabricationById(Long id){
        return fabricationRepository.findById(id);
    }

    public Fabrication createFabrication(Fabrication fabrication){
        return fabricationRepository.save(fabrication);
    }

    public void deleteFabricationById(Long Id){
        fabricationRepository.deleteById(Id);
    }
}
