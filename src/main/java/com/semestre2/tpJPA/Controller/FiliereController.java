package com.semestre2.tpJPA.Controller;

import com.semestre2.tpJPA.Modele.Classe;
import com.semestre2.tpJPA.Modele.Filiere;
import com.semestre2.tpJPA.Repository.FiliereRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class FiliereController {

    private FiliereRepository filiereRepository;


    @GetMapping("/filiere")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "0") int p,
                        @RequestParam(name="size", defaultValue = "6") int s,
                        @RequestParam(name="keyword", defaultValue = "") String kw
    ){
        Page<Filiere> pageFilieres=filiereRepository.findByCodeFilContains(kw, PageRequest.of(p,s));
        model.addAttribute("ListFilieres",pageFilieres.getContent());
        model.addAttribute("pages", new int[pageFilieres.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword", kw);
        return "Filieres";
    }

    @GetMapping("/formFilieres")
    public String formFiliere(Model model){
        model.addAttribute("filiere", new Filiere());
        return "formFilieres";
    }

    @PostMapping("/saveFiliere")
    public String saveFiliere(@Valid Filiere filiere, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "formFilieres";
        }
        filiereRepository.save(filiere);
        return "redirect:/filiere?keyword=" +filiere.getCodeFil();

    }

    @GetMapping("/editFiliere")
    public String editFiliere(Model model, @RequestParam(name = "codeFil") String codeFil) {
        Pageable pageable = PageRequest.of(0, 1); // Exemple de pagination avec une seule page
        Page<Filiere> filieres = filiereRepository.findByCodeFilContains(codeFil, pageable);

        if (filieres.hasContent()) {
            Filiere filiere = filieres.getContent().get(0);
            model.addAttribute("filiere", filiere);
        } else {
            // Gérer le cas où aucun client correspondant n'est trouvé
        }

        return "editFiliere";
    }

    @GetMapping("/deletef")
    public String delete(String codeFil, String keyword, int page){
        if (codeFil != null) {
            filiereRepository.deleteById(codeFil);
        }
        return "redirect:/filiere?page="+page+"&keyword="+keyword;
    }


}
