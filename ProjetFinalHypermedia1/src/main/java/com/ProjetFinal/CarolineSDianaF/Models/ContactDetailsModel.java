package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContactDetailsModel {

    // Attributes
    private String phoneNumber;
    private String email;
    private Integer noCivique;
    private String rue;
    private String noLocal;
    private String ville;
    private String codePostal;

    // Constructors
    public ContactDetailsModel(String phoneNumber, String email, Integer noCivique,String rue,String noLocal, String ville, String codePostal) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.noCivique = noCivique;
        this.rue = rue;
        this.noLocal = noLocal;
        this.ville = ville;
        this.codePostal = codePostal;
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

    public Integer getNoCivique() {
        return noCivique;
    }

    public void setNoCivique(Integer noCivique) {
        this.noCivique = noCivique;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNoLocal() {
        return noLocal;
    }

    public void setNoLocal(String noLocal) {
        this.noLocal = noLocal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    // toString method
    @Override
    public String toString() {
        return "ContactDetailsModel{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + noCivique + '\'' + ", rue='" + rue + '\'' + ", noLocal='" + noLocal + '\'' + ", ville='" + ville + '\'' + ", codePostal='" + codePostal + '\'';
    }
}
