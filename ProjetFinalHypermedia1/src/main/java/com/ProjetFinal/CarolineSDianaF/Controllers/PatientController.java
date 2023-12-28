/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.ClinicService;
import com.ProjetFinal.CarolineSDianaF.Interface.PatientService;
import com.ProjetFinal.CarolineSDianaF.Models.*;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Diana
 */
@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicService clinicService;

    @GetMapping
    public String viewCliniques(Model model) {
        List<ClinicModel> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        return "PatientClinique";
    }

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

    @PostMapping("/update")
    public String updatePatientProfile(@ModelAttribute("patient") PatientModel patient,
                                      @RequestParam Map<String, String> allParams,
                                      Authentication authentication, RedirectAttributes redirectAttributes) {

        // Vérification de l'existence du médecin
        Optional<PatientModel> existingPatientOpt = patientService.getPatientById(patient.getId());
        if (!existingPatientOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Patient avec l'ID " + patient.getId() + " non trouvé.");
            return "redirect:/patients/PatientFiche";
        }

        PatientModel existingPatient = existingPatientOpt.get();

        // Mise à jour des informations de base du patient
        patientService.updatePatient(patient);

        // Sauvegarder les modifications
        patientService.updatePatient(existingPatient);

        redirectAttributes.addFlashAttribute("success", "Profil du patient mis à jour avec succès.");
        return "redirect:/patients/PatientFiche";
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

    // Display the page 'MedecinFiche', the page of redirection after patient login
    @GetMapping("/PatientFiche")
    public String patientFiche(Model model, Authentication authentication) {
        String healthInsuranceNumber = String.valueOf(authentication.getName());
        Optional<PatientModel> patient = patientService.getPatientByHealthInsuranceNumber(healthInsuranceNumber);

        if (patient.isPresent()) {
            PatientModel patientModel = patient.get();
            model.addAttribute("patient", patientModel);
        }

        return "PatientFiche";
    }

    // Display the page 'EspaceConsultation'
    @GetMapping("/EspaceConsultation")
    public String espaceConsultation(Model model, Authentication authentication) {
        String healthInsuranceNumber = String.valueOf(authentication.getName());
        Optional<PatientModel> patient = patientService.getPatientByHealthInsuranceNumber(healthInsuranceNumber);

        if (patient.isPresent()) {
            PatientModel patientModel = patient.get();
            model.addAttribute("patient", patientModel);
        }

        return "EspaceConsultation";
    }

    // Display the page 'PatientClinique'
    @GetMapping("/PatientClinique")
    public String patientClinique(Model model, Authentication authentication) {
        String healthInsuranceNumber = String.valueOf(authentication.getName());
        Optional<PatientModel> patient = patientService.getPatientByHealthInsuranceNumber(healthInsuranceNumber);

        if (patient.isPresent()) {
            PatientModel patientModel = patient.get();
            model.addAttribute("patient", patientModel);
        }

        return "PatientClinique";
    }


}
