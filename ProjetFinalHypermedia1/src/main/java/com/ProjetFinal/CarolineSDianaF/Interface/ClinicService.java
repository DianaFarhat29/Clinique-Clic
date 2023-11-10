/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diana
 */
public interface ClinicService {

    // Méthode par le JPARepository qui ajoute et update
    ClinicModel saveClinic(ClinicModel clinic);

    // Méthode pour lister les détails d'une clinique
    Optional<ClinicModel> viewClinicDetails(Long clinicId);

    // Méthode pour lister les médecins travaillant dans une clinique
    List<DoctorModel> listClinicDoctors(Long clinicId);

    // Méthode pour lister les patients d'une clinique
    List<PatientModel> listClinicPatients(Long clinicId);
