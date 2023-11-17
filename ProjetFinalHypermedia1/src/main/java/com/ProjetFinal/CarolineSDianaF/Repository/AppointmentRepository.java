package com.ProjetFinal.CarolineSDianaF.Repository;

import com.ProjetFinal.CarolineSDianaF.Models.AppointmentModel;
import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)


    // Find all appointments for a given patient
    List<AppointmentModel> findByPatientId(Long patientId);

    // Find all appointments for a given doctor
    List<AppointmentModel> findByDoctorId(Long doctorId);


}
