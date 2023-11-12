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

/**
 *
 * @author Diana
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    // Inject repositories for Doctor, Schedule, Appointment, Email, Document and Patient
    private DoctorRepository doctorRepository;

    private ScheduleRepository scheduleRepository;

    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository,ScheduleRepository scheduleRepository, AppointmentRepository appointmentRepository ) {
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
        this.appointmentRepository = appointmentRepository;
    }

    // Implementation for saving doctor information
    @Override
    public DoctorModel saveDoctor(DoctorModel doctor) {
        return doctorRepository.save(doctor);
    }

    // Implementation for setting availability
    @Override
    public ScheduleModel setAvailability(ScheduleModel schedule) {
        return scheduleRepository.save(schedule);

    }

    // Implementation for updating availability
    @Override
    public void updateAvailability(ScheduleModel newSchedule) {
        // Check if the schedule exists, then update
        ScheduleModel existingSchedule = scheduleRepository.findById(newSchedule.getId())
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

        // Update the necessary fields
        existingSchedule.setStartTime(newSchedule.getStartTime());
        existingSchedule.setEndTime(newSchedule.getEndTime());
        existingSchedule.setDayOfWeek(newSchedule.getDayOfWeek());
        existingSchedule.setStatus(newSchedule.getStatus());
        existingSchedule.setType(newSchedule.getType());

        // Save the appointment in the database
        scheduleRepository.save(existingSchedule);

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
}
