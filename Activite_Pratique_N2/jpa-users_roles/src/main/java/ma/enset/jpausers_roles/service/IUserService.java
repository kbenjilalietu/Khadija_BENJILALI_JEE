package ma.enset.jpausers_roles.service;

import ma.enset.jpausers_roles.entities.Role;
import ma.enset.jpausers_roles.entities.User;

public interface IUserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    User autenticate(String username, String password);
}



