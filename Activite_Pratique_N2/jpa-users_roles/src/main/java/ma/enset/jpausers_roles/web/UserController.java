package ma.enset.jpausers_roles.web;

import ma.enset.jpausers_roles.entities.User;
import ma.enset.jpausers_roles.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/users/{username}")
    public User user(@PathVariable String username){
        User user =  userService.findUserByUserName(username);
        return user;
    }
}















