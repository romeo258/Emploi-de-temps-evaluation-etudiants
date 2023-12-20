package com.semestre2.tpJPA.Repository;

import ch.qos.logback.core.net.server.Client;
import com.semestre2.tpJPA.Modele.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository <Classe, String> {

    Page<Classe> findByCodeClContains(String keyword, Pageable pageable);

}
