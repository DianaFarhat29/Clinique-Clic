/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;

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

}
