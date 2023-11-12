/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Repository;

import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import java.util.List;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Diana
 */
@Repository
public interface ClinicRepository extends JpaRepository<ClinicModel, Long> {

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)

    // Method to find doctor by clinic id
    @Query("SELECT d FROM DoctorModel d WHERE d.clinic.id = :clinicId")
    List<DoctorModel> findDoctorsByClinicId(Long clinicId);

    // Method to find doctor by patient id
    @Query("SELECT p FROM PatientModel p WHERE p.clinic.id = :clinicId")
    List<PatientModel> findPatientsByClinicId(Long clinicId);

    // Method to find clinic by name
    List<ClinicModel> findByName(String name);

    // Method to find clinic by coordinate
    List<ClinicModel> findByCoordinate(String coordinate);

    // Method to find clinic by services
    List<ClinicModel> findByServices(String services);
}
