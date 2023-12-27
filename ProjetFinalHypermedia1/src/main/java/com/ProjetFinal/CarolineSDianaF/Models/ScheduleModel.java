package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Embeddable
public class ScheduleModel {

    // Attributes
    private LocalTime mondayStart;

    private LocalTime mondayEnd;

    private LocalTime tuesdayStart;

    private LocalTime tuesdayEnd;

    private LocalTime wednesdayStart;

    private LocalTime wednesdayEnd;

    private LocalTime thursdayStart;

    private LocalTime thursdayEnd;

    private LocalTime fridayStart;

    private LocalTime fridayEnd;

    private LocalTime saturdayStart;

    private LocalTime saturdayEnd;

    private LocalTime sundayStart;

    private LocalTime sundayEnd;


    // Constructors
    public ScheduleModel(LocalTime mondayStart, LocalTime mondayEnd, LocalTime tuesdayStart, LocalTime tuesdayEnd, LocalTime wednesdayStart, LocalTime wednesdayEnd, LocalTime thursdayStart, LocalTime thursdayEnd, LocalTime fridayStart, LocalTime fridayEnd, LocalTime saturdayStart, LocalTime saturdayEnd, LocalTime sundayStart, LocalTime sundayEnd) {
        this.mondayStart = mondayStart;
        this.mondayEnd = mondayEnd;
        this.tuesdayStart = tuesdayStart;
        this.tuesdayEnd = tuesdayEnd;
        this.wednesdayStart = wednesdayStart;
        this.wednesdayEnd = wednesdayEnd;
        this.thursdayStart = thursdayStart;
        this.thursdayEnd = thursdayEnd;
        this.fridayStart = fridayStart;
        this.fridayEnd = fridayEnd;
        this.saturdayStart = saturdayStart;
        this.saturdayEnd = saturdayEnd;
        this.sundayStart = sundayStart;
        this.sundayEnd = sundayEnd;
    }

    public ScheduleModel() {
        // Empty constructor
    }

    // Getters and setters
    public LocalTime getMondayStart() {
        return mondayStart;
    }

    public void setMondayStart(LocalTime mondayStart) {
        this.mondayStart = mondayStart;
    }

    public LocalTime getMondayEnd() {
        return mondayEnd;
    }

    public void setMondayEnd(LocalTime mondayEnd) {
        this.mondayEnd = mondayEnd;
    }

    public LocalTime getTuesdayStart() {
        return tuesdayStart;
    }

    public void setTuesdayStart(LocalTime tuesdayStart) {
        this.tuesdayStart = tuesdayStart;
    }

    public LocalTime getTuesdayEnd() {
        return tuesdayEnd;
    }


    public void setTuesdayEnd(LocalTime tuesdayEnd) {
        this.tuesdayEnd = tuesdayEnd;
    }

    public LocalTime getWednesdayStart() {
        return wednesdayStart;
    }

    public void setWednesdayStart(LocalTime wednesdayStart) {
        this.wednesdayStart = wednesdayStart;
    }

    public LocalTime getWednesdayEnd() {
        return wednesdayEnd;
    }

    public void setWednesdayEnd(LocalTime wednesdayEnd) {
        this.wednesdayEnd = wednesdayEnd;
    }

    public LocalTime getThursdayStart() {
        return thursdayStart;
    }

    public void setThursdayStart(LocalTime thursdayStart) {
        this.thursdayStart = thursdayStart;
    }

    public LocalTime getThursdayEnd() {
        return thursdayEnd;
    }

    public void setThursdayEnd(LocalTime thursdayEnd) {
        this.thursdayEnd = thursdayEnd;
    }

    public LocalTime getFridayStart() {
        return fridayStart;
    }

    public void setFridayStart(LocalTime fridayStart) {
        this.fridayStart = fridayStart;
    }

    public LocalTime getFridayEnd() {
        return fridayEnd;
    }

    public void setFridayEnd(LocalTime fridayEnd) {
        this.fridayEnd = fridayEnd;
    }

    public LocalTime getSaturdayStart() {
        return saturdayStart;
    }

    public void setSaturdayStart(LocalTime saturdayStart) {
        this.saturdayStart = saturdayStart;
    }

    public LocalTime getSaturdayEnd() {
        return saturdayEnd;
    }

    public void setSaturdayEnd(LocalTime saturdayEnd) {
        this.saturdayEnd = saturdayEnd;
    }

    public LocalTime getSundayStart() {
        return sundayStart;
    }

    public void setSundayStart(LocalTime sundayStart) {
        this.sundayStart = sundayStart;
    }

    public LocalTime getSundayEnd() {
        return sundayEnd;
    }

    public void setSundayEnd(LocalTime sundayEnd) {
        this.sundayEnd = sundayEnd;
    }

    // ToString() Methode
    @Override
    public String toString() {
        return "ScheduleModel{" +
                "mondayStart=" + mondayStart +
                ", mondayEnd=" + mondayEnd +
                ", tuesdayStart=" + tuesdayStart +
                ", tuesdayEnd=" + tuesdayEnd +
                ", wednesdayStart=" + wednesdayStart +
                ", wednesdayEnd=" + wednesdayEnd +
                ", thursdayStart=" + thursdayStart +
                ", thursdayEnd=" + thursdayEnd +
                ", fridayStart=" + fridayStart +
                ", fridayEnd=" + fridayEnd +
                ", saturdayStart=" + saturdayStart +
                ", saturdayEnd=" + saturdayEnd +
                ", sundayStart=" + sundayStart +
                ", sundayEnd=" + sundayEnd +
                '}';
    }
}
