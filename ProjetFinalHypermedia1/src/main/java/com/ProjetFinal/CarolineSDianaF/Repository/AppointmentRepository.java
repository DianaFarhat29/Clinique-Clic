package com.ProjetFinal.CarolineSDianaF.Repository;

import com.ProjetFinal.CarolineSDianaF.Models.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)

    // Define a method to find appointments by patient's ID
    List<AppointmentModel> findByPatientId(Long patientId);
}
