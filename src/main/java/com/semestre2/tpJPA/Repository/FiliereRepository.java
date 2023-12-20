package com.semestre2.tpJPA.Repository;

import com.semestre2.tpJPA.Modele.Filiere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, String> {

    Page<Filiere> findByCodeFilContains(String keyword, Pageable pageable);

}
