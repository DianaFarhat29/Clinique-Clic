package com.ProjetFinal.CarolineSDianaF.Repository;

import com.ProjetFinal.CarolineSDianaF.Models.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, Long> {

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)

    // Find all schedules for a given doctor
    List<ScheduleModel> findByDoctorId(Long doctorId);
}
