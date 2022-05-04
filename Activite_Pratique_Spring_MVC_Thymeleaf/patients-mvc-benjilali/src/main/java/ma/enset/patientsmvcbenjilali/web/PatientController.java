package ma.enset.patientsmvcbenjilali.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import ma.enset.patientsmvcbenjilali.entities.Patient;
import ma.enset.patientsmvcbenjilali.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController ==> pour la proche coté client c-à-d le serveur return Json est le client qui va récuperer html
@Controller
@AllArgsConstructor
public class PatientController
{
    private PatientRepository patientRepository;
    @GetMapping(path = "/user/index")
    public String patientsFunction(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
        model.addAttribute("listePatients", pagePatients.getContent());
        model.addAttribute("pagesNumber", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword", keyword);
        //return une vue qui s'appelle patients.html
        return "patients";
    }

    @GetMapping("/admin/delete")
    public String deleteFunction(Long id,
                                 @RequestParam(defaultValue = "") String keyword,
                                 @RequestParam(defaultValue = "0") int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/") public String home(){
        return "home";
    }

    // Rendu coté client returner une liste Json des patients
    //@RestController
    @GetMapping("/user/patients")
    @ResponseBody //obligatoire
    public List<Patient> patientList(){
        //DispatcherServlet comprendre qu'il s'agit pas d'une vue(String) et ila va convertir on format Json
        return  patientRepository.findAll();
    }

    @GetMapping("/admin/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patientform", new Patient());
        return "formPatients";
    }

    //utiliser pour ajouter et pour faire la mise à jour
    //si l'id est null ==> ajouter
    //sinon ==> modifier
    @PostMapping(path = "/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page){
        //valeur par defaut @RequestParam
        if(bindingResult.hasErrors())
        {if(patient.getId()==null) return "formPatients";
            else return "editPatient";}
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/admin/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patientEdit", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";}

}
