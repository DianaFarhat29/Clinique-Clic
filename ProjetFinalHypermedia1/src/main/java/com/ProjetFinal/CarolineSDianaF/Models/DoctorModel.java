/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Diana
 */
@Entity
@Table(name = "doctors") 
public class DoctorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String speciality;
    
    @Column(nullable = false)
    private Long proffesionalNumber;
    
    @Column(nullable = false)
    private Double expectedSalary;
    
    @Column(nullable = false)
    private String coordinate;
    
    @Column(nullable = false)
    private String location;

    // Contructors
    public DoctorModel(Long id, String lastName, String firstName, String speciality, Long proffesionalNumber, Double expectedSalary, String coordinate, String location) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.speciality = speciality;
        this.proffesionalNumber = proffesionalNumber;
        this.expectedSalary = expectedSalary;
        this.coordinate = coordinate;
        this.location = location;
    }

    public DoctorModel() {
        // Empty Contructor
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Long getProffesionalNumber() {
        return proffesionalNumber;
    }

    public void setProffesionalNumber(Long proffesionalNumber) {
        this.proffesionalNumber = proffesionalNumber;
    }

    public Double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // ToString() Method
    @Override
    public String toString() {
        return "DoctorModel{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", speciality=" + speciality + ", proffesionalNumber=" + proffesionalNumber + ", expectedSalary=" + expectedSalary + ", coordinate=" + coordinate + ", location=" + location + '}';
    }
}
