/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Implementation;

import com.ProjetFinal.CarolineSDianaF.Interface.EmailService;
import com.ProjetFinal.CarolineSDianaF.Interface.PatientService;
import com.ProjetFinal.CarolineSDianaF.Models.*;
import com.ProjetFinal.CarolineSDianaF.Repository.AppointmentRepository;
import com.ProjetFinal.CarolineSDianaF.Repository.DoctorRepository;
import com.ProjetFinal.CarolineSDianaF.Repository.DocumentRepository;
import com.ProjetFinal.CarolineSDianaF.Repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diana
 */
@Service
public class PatientServiceImpl implements PatientService {

    // Inject repositories for Doctor, Appointment, Email, Document and Patient
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EmailService emailService;

    // Logger for logging error messages
    private static final Logger LOGGER = Logger.getLogger(PatientServiceImpl.class.getName());

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public List<PatientModel> getAllPatients() {
        return patientRepository.findAll();
    }

    // Implementation of method to add or upgrade
    @Override
    public PatientModel save(PatientModel patient) {
        return patientRepository.save(patient);
    }

    // Implementation of method to update a patient's information
    @Override
    public PatientModel updatePatient(PatientModel updatedPatient) {
        PatientModel existingPatient = patientRepository.findById(updatedPatient.getId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + updatedPatient.getId()));

        // Update fields of the existing patient
        existingPatient.setFirstName(updatedPatient.getFirstName());
        existingPatient.setLastName(updatedPatient.getLastName());
        existingPatient.setContactDetails(updatedPatient.getContactDetails());

        // Save the updated doctor back to the database
        return patientRepository.save(existingPatient);
    }

    // Implementation of method to book an appointment
    @Override
    public void bookAppointment(AppointmentModel appointment) {
        appointmentRepository.save(appointment);
    }

    // Implementation of method to search for healthcare providers based on criteria
    @Override
    public List<DoctorModel> searchHealthcareProvider(SearchCriteriaModel criteria) {
        // Implement based on criteria fields
        // This is a placeholder example
        return doctorRepository.findBySpeciality(criteria.getSpeciality());
    }

    // Implementation of method to view appointments for a specific patient
    @Override
    public List<AppointmentModel> viewAppointments(Long patientId) {
        // Assuming AppointmentRepository has a method to find appointments by patientId
        return appointmentRepository.findByPatientId(patientId);
    }

    // Implementation of method to update an existing appointment
    @Override
    public void updateAppointment(AppointmentModel appointment) {
        // Check if the appointment exists
        AppointmentModel existingAppointment = appointmentRepository.findById(appointment.getId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        // Update fields of the existing appointment
        existingAppointment.setDoctor(appointment.getDoctor());
        existingAppointment.setDateTime(appointment.getDateTime());
        existingAppointment.setReason(appointment.getReason());
        existingAppointment.setReason(appointment.getStatus());
        existingAppointment.setNotes(appointment.getNotes());

        // Save the updated appointment
        appointmentRepository.save(existingAppointment);
    }

    // Implementation of method to delete an existing appointment
    @Override
    public void cancelAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    // Implementation of method to upload documents related to the patient
    @Override
    public void uploadDocuments(DocumentModel document) {
        documentRepository.save(document);
    }

    // Implementation of method to contact Healthcare Provider
    @Override
    public void contactHealthcareProvider(Long providerId, ContactDetailsModel contactDetails) {
        DoctorModel doctor = doctorRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        String doctorEmail = doctor.getContactDetails().getEmail();
        String subject = "Contact from Patient";
        String message = "Patient contact details: " + contactDetails.getEmail();
        emailService.sendSimpleMessage(doctorEmail, subject, message);
    }

    // Implementation of method to upload files
    String saveUploadedFile(MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);
            try {
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to save uploaded file", e);
            }
        }
        return null;
    }

    // Implementation of method to get a specific appointment by ID
    @Override
    public Optional<AppointmentModel> getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    // Implementation for finding doctor by Professional Number
    @Override
    public Optional<PatientModel> getPatientByHealthInsuranceNumber(String healthInsuranceNumber) {
        return patientRepository.findByHealthInsuranceNumber(healthInsuranceNumber);
    }

    // Implementation for getting a patient by id
    @Override
    public Optional<PatientModel> getPatientById(Long id) {
        return patientRepository.findById(id);
    }


}
