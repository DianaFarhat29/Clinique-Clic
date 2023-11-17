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
public interface AdministratorService {

    // Patient management
    public PatientModel addPatient(PatientModel patient);

    public void removePatient(Long patientId);

    public PatientModel updatePatient(PatientModel patient);

    // Doctor management
    public DoctorModel addDoctor(DoctorModel doctor);

    public void removeDoctor(Long doctorId);

    public DoctorModel updateDoctor(DoctorModel doctor);

    // Clinic management
    public ClinicModel addClinic(ClinicModel clinic);

    public void removeClinic(Long clinicId);

    public ClinicModel updateClinic(ClinicModel clinic);

    // Method to get all patients
    public List<PatientModel> getAllPatients();

    // Method to get a patient by ID
    public Optional<PatientModel> getPatientById(Long id);

    // Method to get all doctors
    public List<DoctorModel> getAllDoctors();

    // Method to get a doctor by ID
    public Optional<DoctorModel> getDoctorById(Long id);

    // Method to get all clinics
    public List<ClinicModel> getAllClinics();

    // Method to get a clinic by ID
    public Optional<ClinicModel> getClinicById(Long id);

}
