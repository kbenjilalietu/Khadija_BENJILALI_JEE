package ma.enset.etudiant_ap.security.repositories;

import ma.enset.etudiant_ap.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String>
{
    AppUser findByUsername(String username);
}
