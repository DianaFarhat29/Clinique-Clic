package com.ProjetFinal.CarolineSDianaF.Repository;

import com.ProjetFinal.CarolineSDianaF.Models.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, Long> {

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)
}
