package itu.s5.bakery.produit.garniture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarnitureRepository extends JpaRepository<Garniture, Long> {
}

