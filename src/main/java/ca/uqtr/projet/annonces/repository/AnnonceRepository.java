package ca.uqtr.projet.annonces.repository;

import ca.uqtr.projet.annonces.model.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findByOwnerId(Long ownerId);
    List<Annonce> findByActiveTrue();
}