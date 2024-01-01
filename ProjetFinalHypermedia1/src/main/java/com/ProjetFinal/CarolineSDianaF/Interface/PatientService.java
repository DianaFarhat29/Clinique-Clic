/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diana
 */
public interface PatientService {

    List<PatientModel> getAllPatients();

    // Method to save or update a patient's information
    PatientModel save(PatientModel patient);

    // Method to update a patient's information
    PatientModel updatePatient(PatientModel patient);

    // Method to book a new appointment
    void bookAppointment(AppointmentModel appointment);

    // Method to search for healthcare providers based on criteria
    List<DoctorModel> searchHealthcareProvider(SearchCriteriaModel criteria);

    // Method to update an existing appointment
    void updateAppointment(AppointmentModel appointment);

    // Method to cancel an appointment
    void cancelAppointment(Long appointmentId);

    // Method to upload documents related to the patient
    void uploadDocuments(DocumentModel document);

    // Method to Contact a healthcare provider
    void contactHealthcareProvider(Long providerId, ContactDetailsModel contactDetails);

    // Method to get a specific appointment by ID
    Optional<AppointmentModel> getAppointmentById(Long appointmentId);

    // Method to find patient by Health Insurance Number
    Optional<PatientModel> getPatientByHealthInsuranceNumber(String healthInsuranceNumber);

    // Method to find patient by id
    Optional<PatientModel> getPatientById(Long id);

    // Method to get patients with doctor
    List<PatientModel> getAllPatientsWithDoctors();

    // Method to add doctor to patient
    void addDoctorToPatient(Long doctorId, Long patientId, Long clinicId);

    // Method to get coming appointments
    List<AppointmentModel> getUpcomingAppointments(Long patientId);

}
