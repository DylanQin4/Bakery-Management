package itu.s5.bakery.p_conseille;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PConseilleRepository extends JpaRepository<PConseille, Long> {
    // Méthodes spécifiques si nécessaire
    List<PConseille> findByEtat(Integer etat);
}
