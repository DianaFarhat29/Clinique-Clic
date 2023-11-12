/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Diana
 */
@Entity
@Table(name = "doctors") 
public class DoctorModel {

    // Attributes
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
    private Long professionalNumber;
    
    @Column(nullable = false)
    private Double expectedSalary;
    
    @Column(nullable = false)
    private String coordinate;
    
    @Column(nullable = false)
    private String location;

    @Embedded
    @Column(nullable = false)
    private ContactDetailsModel contactDetails;

    @ManyToMany
    @JoinTable(
            name = "doctor_clinic",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id")
    )
    private Set<ClinicModel> clinics = new HashSet<>();

    // Constructors
    public DoctorModel(Long id, String lastName, String firstName, String speciality, Long professionalNumber, Double expectedSalary, String coordinate, String location, ContactDetailsModel contactDetails, Set clinics) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.speciality = speciality;
        this.professionalNumber = professionalNumber;
        this.expectedSalary = expectedSalary;
        this.coordinate = coordinate;
        this.location = location;
        this.contactDetails = contactDetails;
        this.clinics = clinics;
    }

    public DoctorModel() {
        // Empty Constructor
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

    public Long getProfessionalNumber() {
        return professionalNumber;
    }

    public void setProfessionalNumber(Long professionalNumber) {
        this.professionalNumber = professionalNumber;
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

    public ContactDetailsModel getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetailsModel contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Set getClinics() {
        return clinics;
    }

    public void setClinics(Set<ClinicModel> clinics) {
        this.clinics = clinics;
    }

    // ToString() Method
    @Override
    public String toString() {
        return "DoctorModel{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", speciality=" + speciality + ", professionalNumber=" + professionalNumber + ", expectedSalary=" + expectedSalary + ", coordinate=" + coordinate + ", location=" + location + ", contactDetails=" + contactDetails + ", clinics=" + clinics + '}';
    }
}
