package ma.enset.jpausers_roles;

import ma.enset.jpausers_roles.entities.Role;
import ma.enset.jpausers_roles.entities.User;
import ma.enset.jpausers_roles.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaUsersRolesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaUsersRolesApplication.class, args);
	}

	@Bean
	CommandLineRunner start(IUserService userService)
	{
		return args -> {
			User user = new User();
			user.setUsername("user1");
			user.setPassword("123456");
			userService.addNewUser(user);

			User user2 = new User();
			user2.setUsername("admin");
			user2.setPassword("123456");
			userService.addNewUser(user2);

			Stream.of("STUDENT", "USER", "ADMIN").forEach(role->{
				Role role1 = new Role();
				role1.setRoleName(role);
				userService.addNewRole(role1);
			});

			userService.addRoleToUser("user1", "STUDENT");
			userService.addRoleToUser("user1", "USER");
			userService.addRoleToUser("admin", "USER");
			userService.addRoleToUser("admin", "ADMIN");

			try {
				User u = userService.autenticate("user1","123456");
				System.out.println(u.getUserId());
				System.out.println(u.getUsername());
				System.out.println("Roles ==> ");
				u.getRoles().forEach(r->{
					System.out.println("Role : "+r);
				});
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		};
	}
}
