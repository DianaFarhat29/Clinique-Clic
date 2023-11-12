package com.ProjetFinal.CarolineSDianaF.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class DocumentModel {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    // Url that stores a link to the document
    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @Column(nullable = false)
    private PatientModel patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @Column(nullable = false)
    private DoctorModel doctor;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String fileFormat;

    // Constructors
    public DocumentModel(Long id, String name, String type, String url, PatientModel patient, DoctorModel doctor, LocalDateTime createDate, LocalDateTime modifiedDate, String description, String fileFormat) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.patient = patient;
        this.doctor = doctor;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.description = description;
        this.fileFormat = fileFormat;
    }

    public DocumentModel() {
        // Empty constructor
    }

    // Getters and setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    // ToString() Method
    @Override
    public String toString() {
        return "DocumentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", description='" + description + '\'' +
                ", fileFormat='" + fileFormat + '\'' +
                '}';
    }
}
