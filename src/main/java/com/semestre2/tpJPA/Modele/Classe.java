package com.semestre2.tpJPA.Modele;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classe")
public class Classe {

    @Id
    @Column(name = "codeCl", length = 6)
    @NotBlank(message = "Le Code ne peut pas être vide")
    private String codeCl;

    @Column(name = "libelle", length = 100)
    @NotBlank(message = "Le ne peut pas être vide")
    private String libelle;

    @Column(name = "niveau", length = 8)
    @NotNull
    private String niveau;


}



