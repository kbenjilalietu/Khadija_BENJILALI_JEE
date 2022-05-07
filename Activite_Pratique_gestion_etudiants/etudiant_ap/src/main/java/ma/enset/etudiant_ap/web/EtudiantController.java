package ma.enset.etudiant_ap.web;

import lombok.AllArgsConstructor;
import ma.enset.etudiant_ap.entities.Etudiant;
import ma.enset.etudiant_ap.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class EtudiantController
{
    private EtudiantRepository etudiantRepository;
    @GetMapping(path = "/user/index")
    public String etudiants(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "5") int size,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword)
    {
        Page<Etudiant> etudiantPage = etudiantRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listEtudiants", etudiantPage.getContent());
        model.addAttribute("pagesNumber", new int[etudiantPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword", keyword);
        return "etudiants";
    }

    @GetMapping("/admin/delete")
    public String deleteFunction(Long id,
                                 @RequestParam(defaultValue = "") String keyword,
                                 @RequestParam(defaultValue = "0") int page){
        etudiantRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/admin/newEtudiant")
    public String newEtudiant(Model model)
    {
        model.addAttribute("etudiant", new Etudiant());
        return "newEtudiant";
    }

    @PostMapping(path = "/admin/save")
    public String save(Model model,  @Valid Etudiant etudiant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page)
    {
        //valeur par defaut @RequestParam
        if(bindingResult.hasErrors())
        {
            if(etudiant.getId()==null)
                return "newEtudiant";
            else
                return "editEtudiant";
        }
        etudiantRepository.save(etudiant);
        return "redirect:/user/index?page="+page;
    }

    @GetMapping("/admin/editEtudiant")
    public String editEtudiant(Model model, Long id, String keyword, int page){
        Etudiant etudiant=etudiantRepository.findById(id).orElse(null);
        if(etudiant==null) throw new RuntimeException("Etudiant introuvable!!!!");
        model.addAttribute("etudiantEdit",etudiant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editEtudiant";
    }

    @GetMapping(value="/login")
    public String login(){
        return "login";
   }

    @GetMapping(value="/logout")
    public String logout(){
        return "logout";
    }
}
