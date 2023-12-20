package com.semestre2.tpJPA.Modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filiere")
public class Filiere {

    @Id
    @Column(name = "codeFil", length = 6)
    @NotNull
    private String codeFil;

    @Column(name = "libelleFil", length = 30)
    @NotBlank(message = "Le Libelle ne peut pas être vide")
    private String libelleFil;

}


