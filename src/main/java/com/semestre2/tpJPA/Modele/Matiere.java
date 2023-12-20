package com.semestre2.tpJPA.Modele;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matiere")
public class Matiere {

    @Id
    @Column(name = "codeMat", length = 8)
    @NotBlank(message = "Le Code ne peut pas être vide")
    private String codeMat;

    @Column(name = "libelleMat", length = 30)
    @NotBlank(message = "Le Libelle ne peut pas être vide")
    private String libelleMat;

    @Column(name = "credit")
    @NotNull
    private int credit;

}



