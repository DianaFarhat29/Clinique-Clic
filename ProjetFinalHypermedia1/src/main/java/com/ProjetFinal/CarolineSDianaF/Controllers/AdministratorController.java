/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.AdministratorService;
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

    /////////////////// ADMIN MANAGEMENT ///////////////////

    @GetMapping("/AdminViewsPatient")
    public String adminPatientFiche(Model model, Authentication authentication) {
        return "AdminViewsPatient";
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
        return "redirect:/admin/patients";
    }

    // Delete a patient
    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable Long id) {
        administratorService.removePatient(id);
        return "redirect:/admin/patients";
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
    @PostMapping("/editDoctor/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute DoctorModel doctor) {
        administratorService.updateDoctor(doctor);
        return "redirect:/admin/doctors";
    }

    // Delete a doctor
    @GetMapping("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        administratorService.removeDoctor(id);
        return "redirect:/admin/doctors";
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
        return "redirect:/admin/clinics";
    }

    // Delete a clinic
    @GetMapping("/deleteClinic/{id}")
    public String deleteClinic(@PathVariable Long id) {
        administratorService.removeClinic(id);
        return "redirect:/admin/clinics";
    }

}
