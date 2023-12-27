/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.CustomUserDetailsService;
import com.ProjetFinal.CarolineSDianaF.Interface.DoctorService;
import com.ProjetFinal.CarolineSDianaF.Models.AppointmentModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.ScheduleModel;
import com.ProjetFinal.CarolineSDianaF.Models.UserModel;
import com.ProjetFinal.CarolineSDianaF.Repository.AppointmentRepository;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Diana
 */

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Display a form to add a new doctor
    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new DoctorModel());
        return "doctor/addDoctor";
    }

    // Process the form to add a new doctor
    @PostMapping("/add")
    public String addDoctor(@ModelAttribute DoctorModel doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    // List all doctors
    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctor/listDoctors";
    }

    // Display a form to update a doctor
    @GetMapping("/edit/{id}")
    public String showEditDoctorForm(@PathVariable Long id, Model model) {
        DoctorModel doctor = doctorService.getDoctorById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        model.addAttribute("doctor", doctor);
        return "doctor/editDoctor";
    }

    @PostMapping("/update")
    public String updateDoctorProfile(@ModelAttribute("doctor") DoctorModel doctor,
                                      @RequestParam Map<String, String> allParams,
                                      Authentication authentication, RedirectAttributes redirectAttributes) {

        // Vérification de l'existence du médecin
        Optional<DoctorModel> existingDoctorOpt = doctorService.getDoctorById(doctor.getId());
        if (!existingDoctorOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Médecin avec l'ID " + doctor.getId() + " non trouvé.");
            return "redirect:/doctors/MedecinFiche";
        }

        DoctorModel existingDoctor = existingDoctorOpt.get();

        // Mise à jour des informations de base du médecin
        doctorService.updateDoctor(doctor);

        // Mise à jour de l'horaire
        ScheduleModel schedule = existingDoctor.getSchedule();
        if (schedule == null) {
            schedule = new ScheduleModel();
            existingDoctor.setSchedule(schedule);
        }

        // Mise à jour des horaires pour chaque jour
        for (DayOfWeek day : DayOfWeek.values()) {
            String dayLower = day.toString().toLowerCase();
            LocalTime startTime = parseTime(allParams.get(dayLower + "Start"));
            LocalTime endTime = parseTime(allParams.get(dayLower + "End"));

            if (startTime != null && endTime != null && !startTime.isBefore(endTime)) {
                redirectAttributes.addFlashAttribute("error", "L'heure de fin doit être après l'heure de début pour " + day);
                return "redirect:/doctors/MedecinFiche";
            }

            // Mise à jour des heures de début et de fin pour chaque jour
            switch (dayLower) {
                case "monday":
                    schedule.setMondayStart(startTime);
                    schedule.setMondayEnd(endTime);
                    break;
                case "tuesday":
                    schedule.setTuesdayStart(startTime);
                    schedule.setTuesdayEnd(endTime);
                    break;
                case "wednesday":
                    schedule.setWednesdayStart(startTime);
                    schedule.setWednesdayEnd(endTime);
                    break;
                case "thursday":
                    schedule.setThursdayStart(startTime);
                    schedule.setThursdayEnd(endTime);
                    break;
                case "friday":
                    schedule.setFridayStart(startTime);
                    schedule.setFridayEnd(endTime);
                    break;
                case "saturday":
                    schedule.setSaturdayStart(startTime);
                    schedule.setSaturdayEnd(endTime);
                    break;
                case "sunday":
                    schedule.setSundayStart(startTime);
                    schedule.setSundayEnd(endTime);
                    break;
            }
        }

        // Sauvegarder les modifications
        existingDoctor.setSchedule(schedule);
        doctorService.updateDoctor(existingDoctor);

        redirectAttributes.addFlashAttribute("success", "Profil du médecin mis à jour avec succès.");
        return "redirect:/doctors/MedecinFiche";
    }

    private LocalTime parseTime(String timeString) {
        if (timeString != null && !timeString.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(timeString, formatter);
        }
        return null;
    }

    // Process the form to update a doctor
    @PostMapping("/edit/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute DoctorModel doctor) {
        doctorService.saveDoctor(doctor); // Using save for both add and update operations
        return "redirect:/doctors";
    }

    // Display form to manage an appointment
    @GetMapping("/{doctorId}/appointments/edit/{appointmentId}")
    public String showEditAppointmentForm(@PathVariable Long doctorId, @PathVariable Long appointmentId, Model model) {
        AppointmentModel appointment = doctorService.getAppointmentById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        model.addAttribute("appointment", appointment);
        return "doctor/editAppointment";
    }

    // Process the form to update or cancel an appointment
    @PostMapping("/{doctorId}/appointments/edit/{appointmentId}")
    public String manageAppointment(@PathVariable Long doctorId, @ModelAttribute AppointmentModel appointment) {
        doctorService.manageAppointment(appointment);
        return "redirect:/doctors/" + doctorId + "/appointments";
    }

    // Display the page 'MedecinFiche', the page of redirection after doctor login
    @GetMapping("/MedecinFiche")
    public String medecinFiche(Model model, Authentication authentication) {
        Long professionalNumber = Long.valueOf(authentication.getName());
        Optional<DoctorModel> doctor = doctorService.getDoctorByProfessionalNumber(professionalNumber);
        if (doctor.isPresent()) {
            DoctorModel doctorModel = doctor.get();
            initializeScheduleAttributes(doctorModel, model);
            model.addAttribute("doctor", doctorModel);
        }
        return "MedecinFiche";
    }

    private void initializeScheduleAttributes(DoctorModel doctorModel, Model model) {
        ScheduleModel schedule = doctorModel.getSchedule();
        if (schedule != null) {
            model.addAttribute("mondayStart", schedule.getMondayStart());
            model.addAttribute("mondayEnd", schedule.getMondayEnd());

            model.addAttribute("tuesdayStart", schedule.getTuesdayStart());
            model.addAttribute("tuesdayEnd", schedule.getTuesdayEnd());

            model.addAttribute("wednesdayStart", schedule.getWednesdayStart());
            model.addAttribute("wednesdayEnd", schedule.getWednesdayEnd());

            model.addAttribute("thursdayStart", schedule.getThursdayStart());
            model.addAttribute("thursdayEnd", schedule.getThursdayEnd());

            model.addAttribute("fridayStart", schedule.getFridayStart());
            model.addAttribute("fridayEnd", schedule.getFridayEnd());

            model.addAttribute("saturdayStart", schedule.getSaturdayStart());
            model.addAttribute("saturdayEnd", schedule.getSaturdayEnd());

            model.addAttribute("sundayStart", schedule.getSundayStart());
            model.addAttribute("sundayEnd", schedule.getSundayEnd());
        }
    }

}
