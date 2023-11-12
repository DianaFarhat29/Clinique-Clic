package com.ProjetFinal.CarolineSDianaF.Repository;

import com.ProjetFinal.CarolineSDianaF.Models.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentModel, Long> {
}
