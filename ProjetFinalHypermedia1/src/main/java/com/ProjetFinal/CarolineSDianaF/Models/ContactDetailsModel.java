package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContactDetailsModel {

    // Attributes
    private String phoneNumber;
    private String email;
    private String address;

    // Constructors
    public ContactDetailsModel(String phoneNumber, String email, String address) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public ContactDetailsModel() {
        // Empty constructor
    }

    // Getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method
    @Override
    public String toString() {
        return "ContactDetailsModel{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
