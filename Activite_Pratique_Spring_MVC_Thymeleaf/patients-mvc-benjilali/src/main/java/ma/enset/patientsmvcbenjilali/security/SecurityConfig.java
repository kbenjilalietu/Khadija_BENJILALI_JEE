package ma.enset.patientsmvcbenjilali.security;

import ma.enset.patientsmvcbenjilali.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity //va etre instancié en premier
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource ;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //pour spécifier les users
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        /*
        String encodePWD = passwordEncoder.encode("1234");
        System.out.println("======================> "+encodePWD);
        //stocker dans la mémoire
        auth.inMemoryAuthentication().withUser("user1")
                .password(passwordEncoder.encode("1111")).roles("USER")
                .and().withUser("user2").password(encodePWD).roles("USER");

        auth.inMemoryAuthentication().withUser("admin").password(encodePWD).roles("USER", "ADMIN");
         */
        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);

         */
        auth.userDetailsService(userDetailsService);
    }

    //pour spécifier les droit d'accès de chaque user
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.formLogin();//je veux utiliser un formulaire d'authentification fournit par Spring Security
        //http.formLogin().loginPage("/login"); ma propre formulaire(créer méthode dans controller)

        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();

        http.authorizeRequests().anyRequest().authenticated();
       // http.authorizeHttpRequests().anyRequest().authenticated();//toutes les requetes http nécissites une authentification

        http.exceptionHandling().accessDeniedPage("/403"); // page 403
    }

}
