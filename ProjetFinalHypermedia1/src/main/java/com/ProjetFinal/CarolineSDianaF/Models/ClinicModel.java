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
    
    // Contructors
    public ClinicModel(Long id, String name, String coordinate, String services) {
        this.id = id;
        this.name = name;
        this.coordinate = coordinate;
        this.services = services;
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
    
    // ToString() Method
    @Override
    public String toString() {
        return "ClinicModel{" + "id=" + id + ", name=" + name + ", coordinate=" + coordinate + ", services=" + services + '}';
    }
       
}
