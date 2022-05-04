package ma.enset.hospitalspringdata.service;
import ma.enset.hospitalspringdata.entities.Consultation;
import ma.enset.hospitalspringdata.entities.Medecin;
import ma.enset.hospitalspringdata.entities.Patient;
import ma.enset.hospitalspringdata.entities.RendezVous;
import ma.enset.hospitalspringdata.repositories.ConsultationRepository;
import ma.enset.hospitalspringdata.repositories.MedecinRepository;
import ma.enset.hospitalspringdata.repositories.PatientRepository;
import ma.enset.hospitalspringdata.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service @Transactional
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository,
                               RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }
    @Override public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }
    @Override public RendezVous saveRDV(RendezVous rendezVous) {
        //générer les chaines alaitoires et unique
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }
    @Override public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
