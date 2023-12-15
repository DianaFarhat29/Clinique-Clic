/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Repository;

import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 *
 * @author Diana
 */
@Repository
public interface ClinicRepository extends JpaRepository<ClinicModel, Long> {

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)

    // Method to find doctor by clinic id
    @Query("SELECT p FROM DoctorModel p JOIN p.clinics c WHERE c.id = :clinicId")
    List<DoctorModel> findDoctorsByClinicId(Long clinicId);

    // Method to find doctor by patient id
    @Query("SELECT p FROM PatientModel p JOIN p.clinics c WHERE c.id = :clinicId")
    List<PatientModel> findPatientsByClinicId(Long clinicId);

    // Method to find clinic by name
    List<ClinicModel> findByName(String name);


    // Method to find clinic by services
    List<ClinicModel> findByServices(String services);

    // Method to find clinic by email
    Optional<ClinicModel> findByContactDetails_Email(String email);


}
