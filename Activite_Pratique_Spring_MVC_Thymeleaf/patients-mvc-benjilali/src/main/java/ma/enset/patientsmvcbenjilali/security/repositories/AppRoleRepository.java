package ma.enset.patientsmvcbenjilali.security.repositories;

import ma.enset.patientsmvcbenjilali.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long>
{
    AppRole findByRoleName(String role);
}
