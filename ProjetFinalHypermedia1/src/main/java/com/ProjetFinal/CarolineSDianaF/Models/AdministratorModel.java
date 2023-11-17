/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.*;

/**
 *
 * @author Diana
 */
@Entity
@Table(name = "administrators") 
public class AdministratorModel {
    
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String username;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    // Constructors
    public AdministratorModel(Long id, String username, UserModel user) {
        this.id = id;
        this.username = username;
        this.user = user;
    }

    public AdministratorModel() {
        //Empty Constructor
    }
    
    // Setters and Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    // toString()
    @Override
    public String toString() {
        return "AdministratorModel{" + "id=" + id + ", username=" + username + ", user=" + user + '}';
    }  
    
}
