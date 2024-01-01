/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.AdministratorService;
import com.ProjetFinal.CarolineSDianaF.Interface.ClinicService;
import com.ProjetFinal.CarolineSDianaF.Interface.DoctorService;
import com.ProjetFinal.CarolineSDianaF.Interface.PatientService;
import com.ProjetFinal.CarolineSDianaF.Models.AdministratorModel;
import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Diana
 */

@Controller
@RequestMapping("/admin")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorService doctorService;




    /////////////////// ADMIN MANAGEMENT ///////////////////

    @GetMapping("/AdminViewsPatient")
    public String adminPatientFiche(Model model, Authentication authentication) {

        List<PatientModel> patients = patientService.getAllPatients();
        model.addAttribute("patients",patients);

        return "AdminViewsPatient";
    }
    @GetMapping("/AdminViewsMedicin")
    public String adminMedecinFiche(Model model, Authentication authentication) {

        List<DoctorModel> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors",doctors);

        return "AdminViewsMedicin";
    }

    @GetMapping("/AdminViewsClinique")
    public String adminCliniqueFiche(Model model, Authentication authentication) {

        List<ClinicModel> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics",clinics);

        return "AdminViewsClinique";
    }

    @GetMapping("/AdminEditClinique/{clinicId}")
    public String adminEditClinique(@PathVariable Long clinicId, Model model) {
        ClinicModel clinic = clinicService.getClinicById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic non trouvée"));
        model.addAttribute("clinic", clinic);
        return "AdminEditClinique";
    }

    @GetMapping("/AdminEditMedecin/{doctorId}")
    public String adminEditMedecin(@PathVariable Long doctorId, Model model) {
        DoctorModel doctor = doctorService.getDoctorById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Médecin non trouvé"));
        model.addAttribute("doctor", doctor);
        return "AdminEditMedecin";
    }

    @GetMapping("/AdminEditPatients/{id}")
    public String adminEditPatients(@PathVariable Long id, Model model) {
        PatientModel patient = patientService.getPatientById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient non trouvé"));
        model.addAttribute("patient", patient);
        return "AdminEditPatients"; //
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



    // Display a form to add a new patient
    @GetMapping("/addPatient")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new PatientModel());
        return "addPatient"; // Thymeleaf template name
    }

    // Process the form to add a new patient
    @PostMapping("/addPatient")
    public String addPatient(@ModelAttribute PatientModel patient) {
        administratorService.addPatient(patient);
        return "redirect:/admin/patients"; // Redirect after adding patient
    }

    // List all patients
    @GetMapping("/patients")
    public String listPatients(Model model) {
        model.addAttribute("patients", administratorService.getAllPatients());
        return "patients"; // Thymeleaf template name
    }

    // Display a form to update a patient
    @GetMapping("/editPatient/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        PatientModel patient = administratorService.getPatientById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        model.addAttribute("patient", patient);
        return "editPatient";
    }

    // Process the form to add a edit a patient
    @PostMapping("/editPatient/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute PatientModel patient) {
        administratorService.updatePatient(patient);
        return "redirect:/admin/AdminViewsPatient";
    }

    // Delete a patient
    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // Attempt to delete the patient
            administratorService.removePatient(id);
            redirectAttributes.addFlashAttribute("success", "Patient deleted successfully.");
        } catch (Exception e) {
            // If an error occurs, show an error message and redirect
            redirectAttributes.addFlashAttribute("error", "Unable to delete the patient. It may be in use elsewhere.");
        }
        return "redirect:/admin/AdminViewsPatient"; // Redirect to the appropriate page
    }

    /////////////////// DOCTOR MANAGEMENT ///////////////////

    // Display a form to add a new doctor
    @GetMapping("/addDoctor")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new DoctorModel());
        return "addDoctor"; // Thymeleaf template name
    }

    // Process the form to add a new doctor
    @PostMapping("/addDoctor")
    public String addDoctor(@ModelAttribute DoctorModel doctor) {
        administratorService.addDoctor(doctor);
        return "redirect:/admin/doctors"; // Redirect after adding doctor
    }

    // List all doctors
    @GetMapping("/doctors")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", administratorService.getAllDoctors());
        return "doctors"; // Thymeleaf template name
    }

    // Display a form to update a doctor
    @GetMapping("/editDoctor/{id}")
    public String showEditDoctorForm(@PathVariable Long id, Model model) {
        DoctorModel doctor = administratorService.getDoctorById(id)
               .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        model.addAttribute("doctor", doctor);
        return "editDoctor";
    }

    // Process the form to add a edit a doctor
    @PostMapping("editDoctor/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute DoctorModel doctor) {
        administratorService.updateDoctor(doctor);
        return "redirect:/admin/AdminViewsMedicin";
    }

    // Delete a doctor
    @GetMapping("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // Attempt to delete the doctor
            administratorService.removeDoctor(id);
            redirectAttributes.addFlashAttribute("success", "Doctor deleted successfully.");
        } catch (Exception e) {
            // If an error occurs, show an error message and redirect
            redirectAttributes.addFlashAttribute("error", "Unable to delete the doctor. It may be in use elsewhere.");
        }
        return "redirect:/admin/AdminViewsMedicin"; // Redirect to the appropriate page
    }

    /////////////////// CLINIC MANAGEMENT ///////////////////

    // Display a form to add a new clinic
    @GetMapping("/addClinic")
    public String showAddClinicForm(Model model) {
        model.addAttribute("clinic", new ClinicModel());
        return "addClinic"; // Thymeleaf template name
    }

    // Process the form to add a new clinic
    @PostMapping("/addClinic")
    public String addClinic(@ModelAttribute ClinicModel clinic) {
        administratorService.addClinic(clinic);
        return "redirect:/admin/clinics"; // Redirect after adding clinic
    }

    // List all clinics
    @GetMapping("/clinics")
    public String listClinics(Model model) {
        model.addAttribute("clinics", administratorService.getAllClinics());
        return "clinics"; // Thymeleaf template name
    }

    // Display a form to update a clinic
    @GetMapping("/editClinic/{id}")
    public String showEditClinicForm(@PathVariable Long id, Model model) {
        ClinicModel clinic = administratorService.getClinicById(id)
              .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        model.addAttribute("clinic", clinic);
        return "editClinic";
    }

    // Process the form to add a edit a clinic
    @PostMapping("/editClinic/{id}")
    public String updateClinic(@PathVariable Long id, @ModelAttribute ClinicModel clinic) {
        administratorService.updateClinic(clinic);
        return "redirect:/admin/AdminViewsClinique";
    }

    // Delete a clinic
    @GetMapping("/deleteClinic/{id}")
    public String deleteClinic(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // Attempt to delete the clinic
            administratorService.removeClinic(id);
            redirectAttributes.addFlashAttribute("success", "Clinic deleted successfully.");
        } catch (Exception e) {
            // If an error occurs, show an error message and redirect
            redirectAttributes.addFlashAttribute("error", "Unable to delete the clinic. It may be in use elsewhere.");
        }
        return "redirect:/admin/AdminViewsClinique"; // Redirect to the appropriate page
    }

}
