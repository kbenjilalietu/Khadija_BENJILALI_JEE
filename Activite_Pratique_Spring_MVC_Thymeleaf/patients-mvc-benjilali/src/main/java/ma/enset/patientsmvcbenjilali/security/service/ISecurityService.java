package ma.enset.patientsmvcbenjilali.security.service;

import ma.enset.patientsmvcbenjilali.security.entities.AppRole;
import ma.enset.patientsmvcbenjilali.security.entities.AppUser;

public interface ISecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
}
