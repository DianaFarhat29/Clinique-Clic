/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diana
 */
public interface ClinicService {

    // JPARepository method that add and update
    ClinicModel save(ClinicModel clinic);

    // Method to list clinic details
    Optional<ClinicModel> viewClinicDetails(Long clinicId);

    // Method to list clinic's doctors details
    List<DoctorModel> listClinicDoctors(Long clinicId);

    // Method to list clinic's patients details
    List<PatientModel> listClinicPatients(Long clinicId);

    // Implementation of method to list all clinics
    List<ClinicModel> getAllClinics();

    // Method to find clinic by Email
    Optional<ClinicModel> getClinicByEmail(String email);

    // Method to find clinic by id
    Optional<ClinicModel> getClinicById(Long id);
}
