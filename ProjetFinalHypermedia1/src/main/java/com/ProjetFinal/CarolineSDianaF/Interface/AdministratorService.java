/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.AdministratorModel;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author Diana
 */
public interface AdministratorService {

    // Injection de dépendances pour que Spring crée les intances des différents repositories
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ClinicRepository clinicRepository;

    // Gestion des patients
    public PatientModel addPatient(PatientModel patient) {
        return patientRepository.save(patient);
    }

    public void removePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    public PatientModel updatePatient(PatientModel patient) {
        return patientRepository.save(patient);
    }

    // Gestion des médecins
    public DoctorModel addDoctor(DoctorModel doctor) {
        return doctorRepository.save(doctor);
    }

    public void removeDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    public DoctorModel updateDoctor(DoctorModel doctor) {
        return doctorRepository.save(doctor);
    }

    // Gestion des cliniques
    public ClinicModel addClinic(ClinicModel clinic) {
        return clinicRepository.save(clinic);
    }

    public void removeClinic(Long clinicId) {
        clinicRepository.deleteById(clinicId);
    }

    public ClinicModel updateClinic(ClinicModel clinic) {
        return clinicRepository.save(clinic);
    }



}
