/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.AppointmentModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.DocumentModel;
import com.ProjetFinal.CarolineSDianaF.Models.ScheduleModel;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diana
 */
public interface DoctorService {

    // Method to Save or update a doctor's information
    DoctorModel saveDoctor(DoctorModel doctor);

    // Method to set a doctor's availability
    ScheduleModel setAvailability(ScheduleModel schedule);

    // Method to update a doctor's availability
    void updateAvailability(ScheduleModel newSchedule);

    // Method to send an appointment invitation
    void sendInvitation(AppointmentModel appointment);

    // Method to send an email reminder for an appointment
    void sendEmailReminder(AppointmentModel appointment);

    // Method to share documents with a patient
    void shareDocumentsWithPatient(Long patientId, DocumentModel document);

    // Method to get a doctor's schedule
    public List<ScheduleModel> getDoctorSchedule(Long doctorId);

    // Method to edit a doctor's schedule
    public ScheduleModel editSchedule(ScheduleModel schedule);

    // Method to get a doctor's appointments
    public List<AppointmentModel> getDoctorAppointments(Long doctorId);

    // Method to manage (update or cancel) an appointment
    public AppointmentModel manageAppointment(AppointmentModel appointment);

    // Method to get a specific schedule by ID
    Optional<ScheduleModel> getScheduleById(Long scheduleId);

    // Method to get a specific appointment by ID
    Optional<AppointmentModel> getAppointmentById(Long appointmentId);

    // Method to get doctor by id
    Optional<DoctorModel> getDoctorById(Long id);

    // Method to get all doctors
    List<DoctorModel> getAllDoctors();

    // Method to find doctor by Professional Number
    Optional<DoctorModel> getDoctorByProfessionalNumber(Long professionalNumber);

}
