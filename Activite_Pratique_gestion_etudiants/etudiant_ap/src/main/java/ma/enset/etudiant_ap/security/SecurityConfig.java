package ma.enset.etudiant_ap.security;

import ma.enset.etudiant_ap.security.service.UserDetailsServiceImpl;
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
        //Sécuriser l'accès à l'application avec un système d'authentification basé sur
        // Spring security en utilisant la stratégie UseDetails Service
        auth.userDetailsService(userDetailsService);
    }

    //pour spécifier les droit d'accès de chaque user
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.formLogin().loginPage("/login").permitAll().and().logout()
                .logoutUrl("/logout") .logoutSuccessUrl("/login");

        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();

        http.authorizeRequests().anyRequest().authenticated();
       // http.authorizeHttpRequests().anyRequest().authenticated();//toutes les requetes http nécissites une authentification

        http.exceptionHandling().accessDeniedPage("/403"); // page 403
    }

}
