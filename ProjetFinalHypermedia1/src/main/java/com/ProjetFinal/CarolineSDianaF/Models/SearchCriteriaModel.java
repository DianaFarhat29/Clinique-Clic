package com.ProjetFinal.CarolineSDianaF.Models;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class SearchCriteriaModel {

    // Attributes
    // Needed doctor's speciality
    private String speciality;
    // Preferred location
    private String location;
    // Preferred doctor
    private String doctorLastName;
    // Preferred clinic
    private String clinicName;
    // Preferred maximum distance
    private Double maxDistance;
    // Preferred day in the week
    private DayOfWeek day;
    // Preferred time in the day
    private LocalTime timeOfDay;

    // Constructors
    public SearchCriteriaModel(String speciality, String location, String doctorLastName, String clinicName, Double maxDistance, DayOfWeek day, LocalTime timeOfDay) {
        this.speciality = speciality;
        this.location = location;
        this.doctorLastName = doctorLastName;
        this.clinicName = clinicName;
        this.maxDistance = maxDistance;
        this.day = day;
        this.timeOfDay = timeOfDay;
    }

    public SearchCriteriaModel() {
        // Empty Constructor
    }

    // Getters and Setters

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    // ToString() Method
    @Override
    public String toString() {
        return "SearchCriteriaModel{" +
                "speciality='" + speciality + '\'' +
                ", location='" + location + '\'' +
                ", doctorLastName='" + doctorLastName + '\'' +
                ", clinicName='" + clinicName + '\'' +
                ", maxDistance=" + maxDistance +
                ", day=" + day +
                ", timeOfDay=" + timeOfDay +
                '}';
    }
}
