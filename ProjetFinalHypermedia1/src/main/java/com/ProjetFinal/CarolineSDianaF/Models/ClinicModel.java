/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Diana
 */
@Entity
@Table(name = "clinics")
public class ClinicModel {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ministerialNumber;

    @Embedded
    @Column(nullable = false)
    private ContactDetailsModel contactDetails;

    @Column(nullable = false)
    private String services;

    @ManyToMany
    @JoinTable(
            name = "clinic_patient",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<PatientModel> patients = new HashSet<>();

    @ManyToMany(mappedBy = "clinics")
    private Set<DoctorModel> doctors = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    // Constructors
    public ClinicModel(Long id, String name, String ministerialNumber , String services,  ContactDetailsModel contactDetails, Set patients, Set doctors, UserModel user) {
        this.id = id;
        this.name = name;
        this.ministerialNumber = ministerialNumber;
        this.services = services;
        this.contactDetails = contactDetails;
        this.patients = patients;
        this.doctors = doctors;
        this.user = user;
    }
    
    public ClinicModel() {
        // Empty constructor
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinisterialNumber() {
        return ministerialNumber;
    }
    public void setMinisterialNumber(String ministerialNumber) {
        this.ministerialNumber = ministerialNumber;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public ContactDetailsModel getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetailsModel contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Set<PatientModel> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientModel> patients) {
        this.patients = patients;
    }
    public Set<DoctorModel> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<DoctorModel> doctors) {
        this.doctors = doctors;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    // Methods to convert services from list to string and vice versa
    public List<String> getServicesAsList() {
        return services != null ? Arrays.asList(services.split(",")) : new ArrayList<>();
    }

    public void setServicesFromList(List<String> servicesList) {
        this.services = String.join(",", servicesList);
    }
    
    // ToString() Method
    @Override
    public String toString() {
        return "ClinicModel{" + "id=" + id + ", ministerialNumber=" + ministerialNumber + ", mi=" + name + ", services=" + services + ", contactDetails=" + contactDetails + ", patients=" + patients + ", doctors=" + doctors + ", user=" + user + '}';
    }
       
}
