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
@Table(name = "clinics") 
public class ClinicModel {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String coordinate;
    
    @Column(nullable = false)
    private String services;

    @Embedded
    @Column(nullable = false)
    private ContactDetailsModel contactDetails;

    @ManyToMany
    @JoinTable(
            name = "clinic_patient",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<ClinicModel> patients = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "clinic_patient",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<ClinicModel> doctors = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    // Constructors
    public ClinicModel(Long id, String name, String coordinate, String services, ContactDetailsModel contactDetails, Set patients, Set doctors, UserModel user) {
        this.id = id;
        this.name = name;
        this.coordinate = coordinate;
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

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
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

    public Set getPatients() {
        return patients;
    }

    public void setPatients(Set<ClinicModel> patients) {
        this.patients = patients;
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
    
    // ToString() Method
    @Override
    public String toString() {
        return "ClinicModel{" + "id=" + id + ", name=" + name + ", coordinate=" + coordinate + ", services=" + services + ", contactDetails=" + contactDetails + ", patients=" + patients + ", doctors=" + doctors + ", user=" + user + '}';
    }
       
}
