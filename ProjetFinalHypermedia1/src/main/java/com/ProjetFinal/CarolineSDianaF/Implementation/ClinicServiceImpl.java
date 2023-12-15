/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Implementation;

import com.ProjetFinal.CarolineSDianaF.Interface.ClinicService;
import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;
import com.ProjetFinal.CarolineSDianaF.Repository.ClinicRepository;
import com.ProjetFinal.CarolineSDianaF.Repository.DoctorRepository;
import com.ProjetFinal.CarolineSDianaF.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diana
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    // Inject repositories for Patient, Doctor, and Clinic
    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    // Implementation of method to add or upgrade
    @Override
    public ClinicModel save(ClinicModel clinic) {
        return clinicRepository.save(clinic);
    }

    // Implementation of method to view clinic details
    @Override
    public Optional<ClinicModel> viewClinicDetails(Long clinicId) {
        return clinicRepository.findById(clinicId);
    }

    // Implementation of method to list the Doctors working at the Clinic
    @Override
    public List<DoctorModel> listClinicDoctors(Long clinicId) {
        return doctorRepository.findByClinicsId(clinicId);
    }

    // Implementation of method to list the Patients at the Clinic
    @Override
    public List<PatientModel> listClinicPatients(Long clinicId) {
        return patientRepository.findByClinicsId(clinicId);
    }

    @Override
    public List<ClinicModel> getAllClinics() {
        return clinicRepository.findAll();
    }

    // Implementation for finding clinic by Email
    @Override
    public Optional<ClinicModel> getClinicByEmail(String email) {
        return clinicRepository.findByContactDetails_Email(email);
    }
}
