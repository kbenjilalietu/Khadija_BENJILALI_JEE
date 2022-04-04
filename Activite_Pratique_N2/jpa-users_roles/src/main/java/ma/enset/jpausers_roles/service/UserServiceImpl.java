package ma.enset.jpausers_roles.service;

import lombok.AllArgsConstructor;
import ma.enset.jpausers_roles.entities.Role;
import ma.enset.jpausers_roles.entities.User;
import ma.enset.jpausers_roles.repositories.RoleRepository;
import ma.enset.jpausers_roles.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
//pour faire l'injection via le constructeur avec paramètres
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
       // user.setPassword();
        return userRepository.save(user);
    }
    @Override public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }
    @Override public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    @Override public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(roleName);
        if(user.getRoles()!=null) {
            //Dans OO ==> ManyToMany
            user.getRoles().add(role);  role.getUsers().add(user);
        }
        // car on la Transaction
        //userRepository.save(user);
    }

    @Override
    public User autenticate(String username, String password) {
       User user = findUserByUserName(username);
       if(user==null) throw new RuntimeException("Bad credentials");
       if(user.getPassword().equals(password))
           return user;
       throw new RuntimeException("Bad credentials");
    }
}
