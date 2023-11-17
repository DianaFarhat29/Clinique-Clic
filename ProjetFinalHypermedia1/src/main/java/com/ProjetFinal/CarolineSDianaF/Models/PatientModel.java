/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Diana
 */
@Entity
@Table(name = "patients") 
public class PatientModel {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private Long healthInsuranceNumber;
    
    @Column(nullable = false)
    private Long sequentialNumber;
    
    @Column(nullable = false)
    private LocalDate dateBirth;
    
    @Column(nullable = false)
    private String gender;

    @Embedded
    @Column(nullable = false)
    private ContactDetailsModel contactDetails;

    @ManyToMany
    @JoinTable(
            name = "patient_clinic",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id")
    )
    private Set<ClinicModel> clinics = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "patient_doctor",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<ClinicModel> doctors = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    // Constructors
    public PatientModel(Long id, String lastName, String firstName, Long healthInsuranceNumber, Long sequentialNumber, LocalDate dateBirth, String gender, ContactDetailsModel contactDetails, Set clinics, Set doctors, UserModel user) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.sequentialNumber = sequentialNumber;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.contactDetails = contactDetails;
        this.clinics = clinics;
        this.doctors = doctors;
        this.user = user;
    }

    public PatientModel() {
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

    public Long getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(Long healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public Long getSequentialNumber() {
        return sequentialNumber;
    }

    public void setSequentialNumber(Long sequentialNumber) {
        this.sequentialNumber = sequentialNumber;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Set getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<ClinicModel> doctors) {
        this.doctors = doctors;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    // toString() Method
    @Override
    public String toString() {
        return "PatientModel{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", healthInsuranceNumber=" + healthInsuranceNumber + ", sequentialNumber=" + sequentialNumber + ", dateBirth=" + dateBirth + ", gender=" + gender + ", contactDetails=" + contactDetails + ", clinics=" + clinics + ", doctors=" + doctors + ", user=" + user + '}';
    }
   
}
