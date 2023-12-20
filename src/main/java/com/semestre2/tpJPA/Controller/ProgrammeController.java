package com.semestre2.tpJPA.Controller;

import com.semestre2.tpJPA.Modele.Filiere;
import com.semestre2.tpJPA.Modele.Matiere;
import com.semestre2.tpJPA.Repository.*;
import com.semestre2.tpJPA.Modele.Classe;
import com.semestre2.tpJPA.Modele.Programme;
import com.semestre2.tpJPA.Repository.ClasseRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProgrammeController {

    private ProgrammeRepository programmeRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private FiliereRepository filiereRepository;


    @GetMapping("/programme")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "0") int p,
                        @RequestParam(name="size", defaultValue = "6") int s,
                        @RequestParam(name="keyword", defaultValue = "") String kw
    ){
        Page<Programme> pageProgrammes=programmeRepository.findByCodeProContains(kw, PageRequest.of(p,s));
        model.addAttribute("ListProgrammes",pageProgrammes.getContent());
        model.addAttribute("pages", new int[pageProgrammes.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword", kw);
        return "Programmes";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/programme";
    }

    @GetMapping("/formProgrammes")
    public String formProgramme(Model model){
        List<Classe> classes = classeRepository.findAll();
        List<Matiere> matieres = matiereRepository.findAll();
        List<Filiere> filieres = filiereRepository.findAll();
        model.addAttribute("classes", classes);
        model.addAttribute("matieres", matieres);
        model.addAttribute("filieres", filieres);
        model.addAttribute("programme", new Programme());
        return "formProgrammes";
    }


    @PostMapping("/saveProgramme")
    public String saveProgramme(@Valid Programme programme, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "formProgrammes";
        }

        try {
            programmeRepository.save(programme);
        } catch (Exception e) {
            // Gérer l'erreur d'enregistrement ici, par exemple, afficher un message d'erreur ou journaliser l'exception
            e.printStackTrace();
            return "formProgrammes";
        }

        return "redirect:/programme?keyword=" +programme.getCodePro();
    }

    @GetMapping("/editProgramme")
    public String editProgramme(Model model, @RequestParam(name = "codePro") String codePro){
        Programme programme=programmeRepository.findById(codePro).get();
        List<Classe> classes = classeRepository.findAll();
        List<Matiere> matieres = matiereRepository.findAll();
        List<Filiere> filieres = filiereRepository.findAll();
        model.addAttribute("classes", classes);
        model.addAttribute("matieres", matieres);
        model.addAttribute("filieres", filieres);
        model.addAttribute("programme",programme);
        return "editProgramme";
    }



    @GetMapping("/deletep")
    public String delete(String codePro, String keyword, int page){
        if (codePro != null) {
            programmeRepository.deleteById(codePro);
        }
        return "redirect:/programme?page="+page+"&keyword="+keyword;
    }

}
