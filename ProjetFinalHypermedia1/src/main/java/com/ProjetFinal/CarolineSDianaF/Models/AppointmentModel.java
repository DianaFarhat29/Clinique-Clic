package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class AppointmentModel {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private DoctorModel doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientModel patient;

    @Column(nullable = false)
    private String reason;

    // This attribute can be null because it's optional
    private String notes;

    // Constructors
    public AppointmentModel(Long id, LocalDateTime dateTime, DoctorModel doctor, PatientModel patient, String reason, String notes) {
        this.id = id;
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.patient = patient;
        this.reason = reason;
        this.notes = notes;
    }

    public AppointmentModel() {
        // Empty constructor
    }

    //  Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // ToString() Method
    @Override
    public String toString() {
        return "AppointmentModel{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", reason='" + reason + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
