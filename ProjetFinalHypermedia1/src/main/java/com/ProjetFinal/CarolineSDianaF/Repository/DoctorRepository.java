/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Repository;

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
public interface DoctorRepository extends JpaRepository<DoctorModel, Long>{

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)

    // Method to find all patients with doctors
    @Query("SELECT c FROM DoctorModel c JOIN FETCH c.patients")
    List<PatientModel> findAllWithDoctors();

    // Method to find doctor by speciality
    List<DoctorModel> findBySpeciality(String speciality);

    // Method to find doctor by clinic
    List<DoctorModel> findByClinicsId(Long clinicId);

    // Method to find doctor by first name
    List<DoctorModel> findByFirstName(String firstName);

    // Method to find doctor by last name
    List<DoctorModel> findByLastName(String lastName);

    // Method to find doctor by professional number
    Optional<DoctorModel> findByProfessionalNumber(Long professionalNumber);
}
