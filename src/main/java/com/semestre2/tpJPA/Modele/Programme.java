package com.semestre2.tpJPA.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "programme")
public class Programme {

    @Id
    @Column(name = "codePro")
    private String codePro;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Selectionnez une date Valide")
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "codeMat",
            joinColumns = @JoinColumn(name = "codePro"),
            inverseJoinColumns = @JoinColumn(name = "codeMat")
    )
    private List<Matiere> matieres;


    @ManyToMany
    @JoinTable(
            name = "codeCl",
            joinColumns = @JoinColumn(name = "codePro"),
            inverseJoinColumns = @JoinColumn(name = "codeCl")
    )
    private List<Classe> classes;


    @ManyToMany
    @JoinTable(
            name = "codeFil",
            joinColumns = @JoinColumn(name = "codePro"),
            inverseJoinColumns = @JoinColumn(name = "codeFil")
    )
    private List<Filiere> filieres;

}


