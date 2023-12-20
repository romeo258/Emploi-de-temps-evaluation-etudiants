package com.semestre2.tpJPA.Controller;


import com.semestre2.tpJPA.Modele.Matiere;
import com.semestre2.tpJPA.Repository.MatiereRepository;
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
public class MatiereController {

    private MatiereRepository matiereRepository;


    @GetMapping("/matiere")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "0") int p,
                        @RequestParam(name="size", defaultValue = "6") int s,
                        @RequestParam(name="keyword", defaultValue = "") String kw
    ){
        Page<Matiere> pageMatieres=matiereRepository.findByCodeMatContains(kw, PageRequest.of(p,s));
        model.addAttribute("ListMatieres",pageMatieres.getContent());
        model.addAttribute("pages", new int[pageMatieres.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword", kw);
        return "Matieres";
    }


    @GetMapping("/formMatieres")
    public String formMatiere(Model model){
        model.addAttribute("matiere", new Matiere());
        return "formMatieres";
    }

    @PostMapping("/saveMatiere")
    public String saveMatiere(@Valid Matiere matiere, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "formMatieres";
        }
        matiereRepository.save(matiere);
        return "redirect:/matiere?keyword=" +matiere.getCodeMat();

    }

    @GetMapping("/editMatiere")
    public String editMatiere(Model model, @RequestParam(name = "codeMat") String codeMat) {
        Pageable pageable = PageRequest.of(0, 1); // pagination avec une seule page
        Page<Matiere> matieres = matiereRepository.findByCodeMatContains(codeMat, pageable);

        if (matieres.hasContent()) {
            Matiere matiere = matieres.getContent().get(0);
            model.addAttribute("matiere", matiere);
        } else {
            // Gérer le cas où aucun client correspondant n'est trouvé
        }

        return "editMatiere";
    }

    @GetMapping("/deletem")
    public String delete(String codeMat, String keyword, int page){
        if (codeMat != null) {
            matiereRepository.deleteById(codeMat);
        }
        return "redirect:/matiere?page="+page+"&keyword="+keyword;
    }

}
