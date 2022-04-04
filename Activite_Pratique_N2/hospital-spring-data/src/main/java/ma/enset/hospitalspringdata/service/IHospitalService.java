package ma.enset.hospitalspringdata.service;
import ma.enset.hospitalspringdata.entities.Consultation;
import ma.enset.hospitalspringdata.entities.Medecin;
import ma.enset.hospitalspringdata.entities.Patient;
import ma.enset.hospitalspringdata.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}

