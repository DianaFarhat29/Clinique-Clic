/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.ClinicService;
import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Diana
 */

@Controller
@RequestMapping("/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

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

    // Display a form to update a clinic
    @GetMapping("/edit/{id}")
    public String showEditClinicForm(@PathVariable Long id, Model model) {
        ClinicModel clinic = clinicService.viewClinicDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        model.addAttribute("clinic", clinic);
        return "clinic/editClinic";
    }

    // Process the form to update a clinic
    @PostMapping("/edit/{id}")
    public String updateClinic(@PathVariable Long id, @ModelAttribute ClinicModel clinic) {
        clinicService.save(clinic); // Using save for both add and update operations
        return "redirect:/clinics";
    }
    
}
