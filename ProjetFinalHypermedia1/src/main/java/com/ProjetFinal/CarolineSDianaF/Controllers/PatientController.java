/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.PatientService;
import com.ProjetFinal.CarolineSDianaF.Models.*;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Diana
 */
@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    // Display a form to book a new appointment
    @GetMapping("/bookAppointment")
    public String showBookAppointmentForm(Model model) {
        model.addAttribute("appointment", new AppointmentModel());
        return "patient/bookAppointment"; // Thymeleaf template for booking appointment
    }

    // Process the form to book a new appointment
    @PostMapping("/bookAppointment")
    public String bookAppointment(@ModelAttribute AppointmentModel appointment) {
        patientService.bookAppointment(appointment);
        return "redirect:/patients/appointments";
    }

    // List all appointments for a patient
    @GetMapping("/appointments")
    public String listAppointments(Model model, @RequestParam Long patientId) {
        model.addAttribute("appointments", patientService.viewAppointments(patientId));
        return "patient/appointments"; // Thymeleaf template for listing appointments
    }

    // Display a form to update an appointment
    @GetMapping("/appointments/edit/{id}")
    public String showEditAppointmentForm(@PathVariable Long id, Model model) {
        AppointmentModel appointment = patientService.getAppointmentById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        model.addAttribute("appointment", appointment);
        return "patient/editAppointment";
    }

    // Process the form to update an appointment
    @PostMapping("/appointments/edit/{id}")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute AppointmentModel appointment) {
        patientService.updateAppointment(appointment);
        return "redirect:/patients/appointments";
    }

    // Cancel an appointment
    @GetMapping("/appointments/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        patientService.cancelAppointment(id);
        return "redirect:/patients/appointments";
    }

    // Display form for uploading a document
    @GetMapping("/uploadDocument")
    public String showUploadDocumentForm(Model model) {
        model.addAttribute("document", new DocumentModel());
        return "patient/uploadDocument";
    }

    // Process the form for uploading a document
    @PostMapping("/uploadDocument")
    public String uploadDocument(@RequestParam("file") MultipartFile file,
                                 @ModelAttribute DocumentModel document,
                                 Model model) {
        try {
            String fileName = saveUploadedFile(file);
            document.setUrl("/path/to/saved/files/" + fileName);
            patientService.uploadDocuments(document);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Failed to upload file.");
            return "patient/uploadDocument"; // The view name for uploading documents
        }
        return "redirect:/patients/documents";
    }


    // Contact a healthcare provider (Doctor)
    @GetMapping("/contactDoctor")
    public String showContactDoctorForm(Model model) {
        model.addAttribute("contactDetails", new ContactDetailsModel());
        return "patient/contactDoctor";
    }

    // Process the form to contact a healthcare provider
    @PostMapping("/contactDoctor")
    public String contactDoctor(@RequestParam Long doctorId, @ModelAttribute ContactDetailsModel contactDetails) {
        patientService.contactHealthcareProvider(doctorId, contactDetails);
        return "redirect:/patients";
    }

    // Display form for uploading a file
    private String saveUploadedFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot save empty file");
        }

        // Define the directory where files will be stored
        String uploadDir = "uploaded-files/";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file with its original filename
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName; // Return the filename to store it in the database or use it later
    }


}
