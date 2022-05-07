package ma.enset.etudiant_ap.repositories;

import ma.enset.etudiant_ap.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Page<Etudiant> findByNomContains(String keyword, Pageable pageable);
}
