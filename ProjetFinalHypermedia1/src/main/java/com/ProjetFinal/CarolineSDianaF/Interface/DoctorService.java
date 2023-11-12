/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.*;

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


}
