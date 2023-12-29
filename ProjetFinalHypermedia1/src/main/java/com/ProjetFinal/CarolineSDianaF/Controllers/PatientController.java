/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.ClinicService;
import com.ProjetFinal.CarolineSDianaF.Interface.DoctorService;
import com.ProjetFinal.CarolineSDianaF.Interface.PatientService;
import com.ProjetFinal.CarolineSDianaF.Interface.AppointmentService;
import com.ProjetFinal.CarolineSDianaF.Models.*;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/getAvailableSlots/{doctorId}/{date}")
    public ResponseEntity<List<String>> getAvailableSlots(@PathVariable Long doctorId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        System.out.println("Received date: " + date);
        System.out.println("Received doctorId: " + doctorId);
        try {
            // Utilisation de la méthode depuis DoctorServiceImpl
            List<String> availableSlots = doctorService.calculateAvailableSlots(doctorId, date);

            return ResponseEntity.ok(availableSlots);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/bookAppointment")
    public String bookAppointment(@RequestBody AppointmentDTO appointmentDTO, Authentication authentication, RedirectAttributes redirectAttributes) {

        try {
            // Convertir la date et l'heure en LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(appointmentDTO.getAppointmentDate() + "T" + appointmentDTO.getAppointmentTime());

            // Récupérer le patient authentifié
            String healthInsuranceNumber = authentication.getName();
            PatientModel patient = patientService.getPatientByHealthInsuranceNumber(healthInsuranceNumber)
                    .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

            // Récupérer le médecin sélectionné
            DoctorModel doctor = doctorService.getDoctorById(appointmentDTO.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

            // Créer le nouvel AppointmentModel
            AppointmentModel appointment = new AppointmentModel();
            appointment.setDateTime(dateTime);
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setReason(appointmentDTO.getAppointmentReason());

            // Enregistrer le rendez-vous
            patientService.bookAppointment(appointment);

            // Ajouter un message de succès
            redirectAttributes.addFlashAttribute("successMessage", "Rendez-vous pris avec succès.");

            return "redirect:/patients/EspaceConsultation";
        } catch (Exception e) {
            // Gérer les erreurs
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de la prise de rendez-vous : " + e.getMessage());
            return "redirect:/patients/EspaceConsultation";
        }
    }

    // Cancel an appointment
    @GetMapping("/appointments/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        patientService.cancelAppointment(id);
        return "redirect:/patients/appointments";
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
            model.addAttribute("patientId", patientModel.getId());

            // Récupérer la liste des rendez-vous à venir du patient
            List<AppointmentModel> upcomingAppointments = patientService.getUpcomingAppointments(patientModel.getId());
            model.addAttribute("upcomingAppointments", upcomingAppointments);
        }
        return "EspaceConsultation";
    }

    // Display the page 'PatientClinique'
    @GetMapping("/PatientClinique")
    public String patientClinique(Model model, Authentication authentication) {
        List<ClinicModel> clinicsWithDoctors = clinicService.getAllClinicsWithDoctors();
        model.addAttribute("clinics", clinicsWithDoctors);

        List<PatientModel> patientsWithDoctors = patientService.getAllPatientsWithDoctors();
        model.addAttribute("patients", patientsWithDoctors);

        String healthInsuranceNumber = String.valueOf(authentication.getName());
        Optional<PatientModel> patient = patientService.getPatientByHealthInsuranceNumber(healthInsuranceNumber);
        List<ClinicModel> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        if (patient.isPresent()) {
            PatientModel patientModel = patient.get();
            model.addAttribute("patient", patientModel);
        }

        return "PatientClinique";
    }

    @PostMapping("/addDoctor")
    public String addDoctorToPatient(@RequestParam("doctorId") Long doctorId,
                                     @RequestParam("patientId") Long patientId,
                                     RedirectAttributes redirectAttributes) {
        try {
            patientService.addDoctorToPatient(doctorId, patientId);
            redirectAttributes.addFlashAttribute("successMessage", "Médecin ajouté avec succès.");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Patient ou médecin introuvable.");
        }
        return "redirect:/patients/PatientClinique";
    }


}
