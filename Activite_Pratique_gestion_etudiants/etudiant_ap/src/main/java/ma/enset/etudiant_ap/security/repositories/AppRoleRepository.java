package ma.enset.etudiant_ap.security.repositories;

import ma.enset.etudiant_ap.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long>
{
    AppRole findByRoleName(String role);
}
