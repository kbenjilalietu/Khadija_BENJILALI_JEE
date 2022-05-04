package ma.enset.hospitalspringdata.repositories;
import ma.enset.hospitalspringdata.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}

