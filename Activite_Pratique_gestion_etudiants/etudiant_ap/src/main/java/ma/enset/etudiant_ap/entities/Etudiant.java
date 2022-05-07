package ma.enset.etudiant_ap.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 4, max = 40)
    private String nom;
    @NotEmpty
    @Size(min = 4, max = 40)
    private String prenom;
    @Column(unique = true)
    @NotEmpty
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean regle;
    @Enumerated(EnumType.STRING)
    private Genre genre;


}
