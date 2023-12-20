package com.semestre2.tpJPA.Repository;

import com.semestre2.tpJPA.Modele.Classe;
import com.semestre2.tpJPA.Modele.Programme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, String> {

    Page<Programme> findByCodeProContains(String keyword, Pageable pageable);

}
