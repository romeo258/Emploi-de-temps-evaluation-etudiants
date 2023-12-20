package com.semestre2.tpJPA.Controller;

import com.semestre2.tpJPA.Modele.Classe;
import com.semestre2.tpJPA.Repository.ClasseRepository;
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
public class ClasseController {

    private ClasseRepository classeRepository;


    @GetMapping("/classe")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "0") int p,
                        @RequestParam(name="size", defaultValue = "6") int s,
                        @RequestParam(name="keyword", defaultValue = "") String kw
    ){
        Page<Classe> pageClasses=classeRepository.findByCodeClContains(kw, PageRequest.of(p,s));
        model.addAttribute("ListClasses",pageClasses.getContent());
        model.addAttribute("pages", new int[pageClasses.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword", kw);
        return "Classes";
    }


    @GetMapping("/formClasses")
    public String formClasse(Model model){
        model.addAttribute("classe", new Classe());
        return "formClasses";
    }

    @PostMapping("/saveClasse")
    public String saveClasse(@Valid Classe classe, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "formClasses";
        }
        classeRepository.save(classe);
        return "redirect:/classe?keyword=" +classe.getCodeCl();

    }

    @GetMapping("/editClasse")
    public String editClient(Model model, @RequestParam(name = "codeCl") String codeCl) {
        Pageable pageable = PageRequest.of(0, 1); // Exemple de pagination avec une seule page
        Page<Classe> classes = classeRepository.findByCodeClContains(codeCl, pageable);

        if (classes.hasContent()) {
            Classe classe = classes.getContent().get(0);
            model.addAttribute("classe", classe);
        } else {
            // Gérer le cas où aucun client correspondant n'est trouvé
        }

        return "editClasse";
    }

    @GetMapping("/deletec")
    public String delete(String codeCl, String keyword, int page){
        if (codeCl != null) {
            classeRepository.deleteById(codeCl);
        }
        return "redirect:/classe?page="+page+"&keyword="+keyword;
    }
}
