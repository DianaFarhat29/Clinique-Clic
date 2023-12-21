package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
public class ScheduleModel {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private DoctorModel doctor;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private DayOfWeek dayOfWeek;

    // Constructors
    public ScheduleModel(Long id, DoctorModel doctor, LocalDateTime startTime, LocalDateTime endTime, DayOfWeek dayOfWeek) {
        this.id = id;
        this.doctor = doctor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    public ScheduleModel() {
        // Empty constructor
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    // ToString() Method
    @Override
    public String toString() {
        return "ScheduleModel{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
