package itu.s5.bakery.vente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailVenteRepository extends JpaRepository<DetailVente, Long> {
    List<DetailVente> findByVente(Vente vente);
}
