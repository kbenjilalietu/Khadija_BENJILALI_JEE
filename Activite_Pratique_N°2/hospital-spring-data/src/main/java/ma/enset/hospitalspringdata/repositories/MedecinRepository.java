package ma.enset.hospitalspringdata.repositories;
import ma.enset.hospitalspringdata.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    List<Medecin> findByNom(String nom);
}
