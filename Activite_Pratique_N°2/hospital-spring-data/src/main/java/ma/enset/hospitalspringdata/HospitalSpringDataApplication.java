package ma.enset.hospitalspringdata;
import ma.enset.hospitalspringdata.entities.*;
import ma.enset.hospitalspringdata.repositories.ConsultationRepository;
import ma.enset.hospitalspringdata.repositories.MedecinRepository;
import ma.enset.hospitalspringdata.repositories.PatientRepository;
import ma.enset.hospitalspringdata.repositories.RendezVousRepository;
import ma.enset.hospitalspringdata.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalSpringDataApplication  {
    public static void main(String[] args)
    {
        SpringApplication.run(HospitalSpringDataApplication.class, args);
    }

    @Bean
   CommandLineRunner start(IHospitalService hospitalService, PatientRepository patientRepository,
                           MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository)
    {
        return args -> {
            //liste des patients
            Stream.of("Ahlam","Jamil","Farah").forEach(name->{
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setDateNaissance(new Date());
                patient.setMalade(Math.random()>0.5?true:false);
                hospitalService.savePatient(patient);
            });
            //liste des medecins
            Stream.of("Ali", "Khadija", "Asmaa").forEach(name->{
                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setEmail(name+"@gmail.com");
                medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                hospitalService.saveMedecin(medecin);
            });


            Patient patient = patientRepository.findById(1L).orElse(null);
            Medecin medecin = medecinRepository.findById(1L).orElse(null);
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.DONE);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            hospitalService.saveRDV(rendezVous);

            RendezVous rendezVous1 = rendezVousRepository.findAll().get(0);
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(rendezVous1.getDate());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation ....");
            hospitalService.saveConsultation(consultation);
        };
   }
}
