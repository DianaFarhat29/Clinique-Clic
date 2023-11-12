/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.*;
import java.util.List;

/**
 *
 * @author Diana
 */
public interface PatientService {

    // Method to save or update a patient's information
    PatientModel save(PatientModel patient);

    // Method to book a new appointment
    void bookAppointment(AppointmentModel appointment);

    // Method to search for healthcare providers based on criteria
    List<DoctorModel> searchHealthcareProvider(SearchCriteriaModel criteria);

    // Method to view appointments for a specific patient
    List<AppointmentModel> viewAppointments(Long patientId);

    // Method to update an existing appointment
    void updateAppointment(AppointmentModel appointment);

    // Method to cancel an appointment
    void cancelAppointment(Long appointmentId);

    // Method to upload documents related to the patient
    void uploadDocuments(DocumentModel document);

    // Method to Contact a healthcare provider
    void contactHealthcareProvider(Long providerId, ContactDetailsModel contactDetails);
}
