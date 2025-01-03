package itu.s5.bakery.fabrication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricationRepository extends JpaRepository<Fabrication, Long>{

    
}