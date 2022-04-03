package ma.enset.hospitalspringdata.web;
import ma.enset.hospitalspringdata.entities.Medecin;
import ma.enset.hospitalspringdata.entities.Patient;
import ma.enset.hospitalspringdata.repositories.MedecinRepository;
import ma.enset.hospitalspringdata.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedecinRepository medecinRepository;

    @GetMapping("/patients")
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }

    @GetMapping("/medecins")
    public List<Medecin> medecinList()
    {
        return medecinRepository.findAll();
    }
}

