package ma.enset.etudiant_ap;

import ma.enset.etudiant_ap.entities.Etudiant;
import ma.enset.etudiant_ap.entities.Genre;
import ma.enset.etudiant_ap.repositories.EtudiantRepository;
import ma.enset.etudiant_ap.security.service.ISecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class EtudiantApApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudiantApApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
   // @Bean
    CommandLineRunner start(EtudiantRepository etudiantRepository){
        return args -> {
            etudiantRepository.save(
                    new Etudiant(null, "BENJILALI","Khadija","khadija@gmail.com", Date.valueOf("2000-01-27"), Genre.FEMININ, true));
            etudiantRepository.save(
                    new Etudiant(null, "MAJOULI","Ahlam","ahlam@gmail.com", Date.valueOf("2001-02-07"), Genre.FEMININ, false));
            etudiantRepository.save(
                    new Etudiant(null, "KOULAMI","Akram","akram@gmail.com", Date.valueOf("2000-04-20"), Genre.MASCULIN, true));
            etudiantRepository.save(
                    new Etudiant(null, "DOUKALI","Mohammed","mohammed@gmail.com", Date.valueOf("2000-01-22"), Genre.MASCULIN, true));

            etudiantRepository.findAll().forEach(e-> System.out.println("Etudiant ==> "+e.getNom()+" "+e.getPrenom()));
        };
    }
    //@Bean
    CommandLineRunner saveUsers(ISecurityService iSecurityService){
        return args -> {
            iSecurityService.saveNewUser("khadija","1234","1234");
            iSecurityService.saveNewUser("Akram","1234","1234");

            iSecurityService.saveNewRole("ADMIN","");
            iSecurityService.saveNewRole("USER","");

            iSecurityService.addRoleToUser("khadija","ADMIN");
            iSecurityService.addRoleToUser("khadija","USER");
            iSecurityService.addRoleToUser("Akram","USER");

        };
    }

}
