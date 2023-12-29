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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author Diana
 */

@Controller
@RequestMapping("/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PatientService patientService;

    // Display a form to add a new clinic
    @GetMapping("/add")
    public String showAddClinicForm(Model model) {
        model.addAttribute("clinic", new ClinicModel());
        return "clinic/addClinic";
    }

    // Process the form to add a new clinic
    @PostMapping("/add")
    public String addClinic(@ModelAttribute ClinicModel clinic) {
        clinicService.save(clinic);
        return "redirect:/clinics";
    }

    // List all clinics
    @GetMapping
    public String listClinics(Model model) {
        model.addAttribute("clinics", clinicService.getAllClinics());
        return "clinic/listClinics";
    }

    // Display clinic details including doctors and patients
    @GetMapping("/{id}")
    public String viewClinicDetails(@PathVariable Long id, Model model) {
        ClinicModel clinic = clinicService.viewClinicDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        model.addAttribute("clinic", clinic);
        model.addAttribute("doctors", clinicService.listClinicDoctors(id));
        model.addAttribute("patients", clinicService.listClinicPatients(id));
        return "clinic/viewClinic";
    }

    @PostMapping("/update")
    public String updateClinicProfile(@ModelAttribute("clinic") ClinicModel clinic,
                                      @RequestParam Map<String, String> allParams,
                                      Authentication authentication, RedirectAttributes redirectAttributes) {

        // Verification that the clinic exists
        Optional<ClinicModel> existingClinicOpt = clinicService.getClinicById(clinic.getId());
        if (!existingClinicOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Clinic avec l'ID " + clinic.getId() + " non trouvé.");
            return "redirect:/doctors/MedecinFiche";
        }

        ClinicModel existingClinic = existingClinicOpt.get();

        // Update clinic details
        clinicService.updateClinic(clinic);
        clinicService.updateClinic(existingClinic);

        redirectAttributes.addFlashAttribute("success", "Profil de la clinique mis à jour avec succès.");
        return "redirect:/clinics/CliniqueFiche";
    }

    private LocalTime parseTime(String timeString) {
        if (timeString != null && !timeString.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(timeString, formatter);
        }
        return null;
    }

    // Process the form to update a clinic
    @PostMapping("/edit/{id}")
    public String updateClinic(@PathVariable Long id, @ModelAttribute ClinicModel clinic) {
        clinicService.save(clinic); // Using save for both add and update operations
        return "redirect:/clinics";
    }

    // Display a form to update a clinic
    @GetMapping("/CliniqueFiche")
    public String cliniqueFiche(Model model, Authentication authentication) {
        String email = String.valueOf(authentication.getName());
        Optional<ClinicModel> clinic = clinicService.getClinicByEmail(email);

        if (clinic.isPresent()) {
            ClinicModel clinicModel = clinic.get();
            List<String> allServices = getListOfAllServices();

            Set<String> clinicServices = new HashSet<>(Arrays.asList(clinicModel.getServices().split(", ")));
            model.addAttribute("clinic", clinicModel);
            model.addAttribute("allServices", allServices);
            model.addAttribute("clinicServices", clinicServices);
        }

        return "CliniqueFiche";
    }

    @GetMapping("/CliniquePatients/{clinicId}")
    public String cliniquePatients(Model model, Authentication authentication) {
        String email = String.valueOf(authentication.getName());
        Optional<ClinicModel> clinicOpt = clinicService.getClinicByEmail(email);

        if (clinicOpt.isPresent()) {
            ClinicModel clinicModel = clinicOpt.get();
            Set<PatientModel> patients = clinicModel.getPatients();

            model.addAttribute("patients", patients);
            model.addAttribute("clinic", clinicModel);
        }

        return "CliniquePatients";
    }

    @GetMapping("/CliniqueMedecin/{clinicId}")
    public String cliniqueMedecin(Model model, Authentication authentication) {
        String email = String.valueOf(authentication.getName());
        Optional<ClinicModel> clinicOpt = clinicService.getClinicByEmail(email);

        if (clinicOpt.isPresent()) {
            ClinicModel clinicModel = clinicOpt.get();
            Set<DoctorModel> doctors = clinicModel.getDoctors();
            model.addAttribute("doctors", doctors);
            model.addAttribute("clinic", clinicModel);
        }

        return "CliniqueMedecin";
    }

    private List<String> getListOfAllServices() {
        // Retourne une liste de tous les services possibles
        return Arrays.asList("Dermatologie", "Cardiologie", "Gynécologie", "Orthopédie", "Ophtalmologie", " Oto-rhino-laryngologie (ORL)", "Psychiatrie", "Radiologie", "Endocrinologie", "Urologie", "Neurologie", "Gastro-entérologie", "Chirurgie générale", "Anesthésiologie", "Kinésithérapie", "Dentisterie", "Oncologie", "Immunologie" );
    }


    
}
