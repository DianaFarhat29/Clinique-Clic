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
import java.time.LocalDate;

/**
 *
 * @author Diana
 */
@Entity
@Table(name = "patients") 
public class PatientModel {
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

    // Contructors
    public PatientModel(Long id, String lastName, String firstName, Long healthInsuranceNumber, Long sequentialNumber, LocalDate dateBirth, String gender) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.sequentialNumber = sequentialNumber;
        this.dateBirth = dateBirth;
        this.gender = gender;
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

    // toString() Method
    @Override
    public String toString() {
        return "PatientModel{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", healthInsuranceNumber=" + healthInsuranceNumber + ", sequentialNumber=" + sequentialNumber + ", dateBirth=" + dateBirth + ", gender=" + gender + '}';
    }
   
}
