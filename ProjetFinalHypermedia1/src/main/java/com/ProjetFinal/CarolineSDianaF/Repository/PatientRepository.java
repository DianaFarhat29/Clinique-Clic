/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Repository;


import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author Diana
 */

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)

    // Method to find patient by first name
    List<PatientModel> findByFirstName(String firstName);

    // Method to find patient by last name
    List<PatientModel> findByLastName(String lastName);

    // Method to find patient by first and last name
    List<PatientModel> findByFirstNameAndLastName(String firstName, String lastName);

    // Method to find patient by date of birth
    List<PatientModel> findByDateBirth(LocalDate dateBirth);

    // Method to find patient by gender
    List<PatientModel> findByGender(String gender);

    // Method to find patient by Health Insurance Number
    Optional<PatientModel> findByHealthInsuranceNumber(String healthInsuranceNumber);

    // Method to find patient by Health Sequential Number
    Optional<PatientModel> findByHealthSequentialNumber(Long healthSequentialNumber);

    // Method to find patient by clinic
    List<PatientModel> findByClinicsId(Long clinicId);

}
