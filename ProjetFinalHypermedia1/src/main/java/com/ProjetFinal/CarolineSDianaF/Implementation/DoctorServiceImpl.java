/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Implementation;


import com.ProjetFinal.CarolineSDianaF.Interface.DoctorService;
import com.ProjetFinal.CarolineSDianaF.Interface.EmailService;
import com.ProjetFinal.CarolineSDianaF.Models.*;
import com.ProjetFinal.CarolineSDianaF.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diana
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    // Inject repositories for Doctor, Schedule, Appointment, Email, Document and Patient
    private DoctorRepository doctorRepository;

    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailService emailService;

    private DoctorService doctorService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository,AppointmentRepository appointmentRepository ) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    // Implementation for saving doctor information
    @Override
    public DoctorModel saveDoctor(DoctorModel doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public DoctorModel updateDoctor(DoctorModel updatedDoctor) {
        DoctorModel existingDoctor = doctorRepository.findById(updatedDoctor.getId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + updatedDoctor.getId()));

        // Update fields of the existing doctor
        existingDoctor.setFirstName(updatedDoctor.getFirstName());
        existingDoctor.setLastName(updatedDoctor.getLastName());
        existingDoctor.setSpeciality(updatedDoctor.getSpeciality());
        existingDoctor.setProfessionalNumber(updatedDoctor.getProfessionalNumber());
        existingDoctor.setExpectedSalary(updatedDoctor.getExpectedSalary());
        existingDoctor.setContactDetails(updatedDoctor.getContactDetails());


        // Save the updated doctor back to the database
        return doctorRepository.save(existingDoctor);
    }

    // Implementation for sending an appointment invitation
    @Override
    public void sendInvitation(AppointmentModel appointment) {
        // Save the appointment in the database
        appointmentRepository.save(appointment);

        // Prepare the email details
        String subject = "New Appointment Invitation";
        String patientEmail = appointment.getPatient().getContactDetails().getEmail();
        String message = "You have been invited to an appointment on " + appointment.getDateTime().toString() +
                "\nReason: " + appointment.getReason() +
                "\nWith Doctor: " + appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName();

        // Send the email invitation
        emailService.sendSimpleMessage(patientEmail, subject, message);

    }

    // Implementation for sending an email reminder
    @Override
    public void sendEmailReminder(AppointmentModel appointment) {
        PatientModel patient = appointment.getPatient();
        ContactDetailsModel contactDetails = patient.getContactDetails();
        String patientEmail = contactDetails.getEmail();

        String subject = "Appointment Reminder";
        String text = "You have an appointment on " + appointment.getDateTime().toString();

        emailService.sendSimpleMessage(patientEmail, subject, text);
    }

    // Implementation for sharing documents with a patient
    @Override
    public void shareDocumentsWithPatient(Long patientId, DocumentModel  document) {
        // Fetch the patient based on patientId
        PatientModel patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientId));

        // Associate the document with the patient
        document.setPatient(patient);
    }

    // Implementation for getting a doctor's appointments
    @GetMapping("/{doctorId}/appointments")
    public String viewDoctorAppointments(@PathVariable Long doctorId, Model model) {
        List<AppointmentModel> appointments = doctorService.getDoctorAppointments(doctorId);  // Fetch appointments from service
        model.addAttribute("appointments", appointments);
        return "doctorAppointments"; // Replace with the name of your view template
    }


    // Implementation for managing (update or cancel) an appointment
    public AppointmentModel manageAppointment(AppointmentModel appointment) {
        // Update or cancel the appointment based on its status or other fields
        return appointmentRepository.save(appointment);
    }

    // Implementation for getting appointment by id
    public Optional<AppointmentModel> getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    // Implementation for getting a doctor's appointments
    @Override
    public List<AppointmentModel> getDoctorAppointments(Long doctorId) {
        // Fetch and return the list of appointments for the doctor
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Implementation for getting a doctor by id
    @Override
    public Optional<DoctorModel> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Implementation for getting all doctor
    @Override
    public List<DoctorModel> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Implementation for finding doctor by Professional Number
    @Override
    public Optional<DoctorModel> getDoctorByProfessionalNumber(Long professionalNumber) {
        return doctorRepository.findByProfessionalNumber(professionalNumber);
    }

}
