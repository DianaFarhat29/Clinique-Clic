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


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
                                      Authentication authentication) {

        // Update basic doctor information
        doctorService.updateDoctor(doctor);

        // Update each day's schedule
        for (DayOfWeek day : DayOfWeek.values()) {
            String openTimeKey = day.toString().toLowerCase() + "Start";
            String closeTimeKey = day.toString().toLowerCase() + "End";

            String openTimeString = allParams.get(openTimeKey);
            String closeTimeString = allParams.get(closeTimeKey);

            LocalTime openTime = null;
            LocalTime closeTime = null;

            // Parse the times if they are provided
            if (openTimeString != null && !openTimeString.isEmpty()) {
                openTime = LocalTime.parse(openTimeString);
            }
            if (closeTimeString != null && !closeTimeString.isEmpty()) {
                closeTime = LocalTime.parse(closeTimeString);
            }

            // Retrieve or create a new schedule object for the day
            ScheduleModel schedule = doctor.getSchedules().stream()
                    .filter(s -> s.getDayOfWeek() == day)
                    .findFirst()
                    .orElse(new ScheduleModel());

            // Update the schedule object
            schedule.setDayOfWeek(day);
            if (openTime != null) {
                schedule.setStartTime(LocalDateTime.of(LocalDate.now(), openTime));
            }
            if (closeTime != null) {
                schedule.setEndTime(LocalDateTime.of(LocalDate.now(), closeTime));
            }
            schedule.setDoctor(doctor);

            // Update or save the schedule
            doctorService.editSchedule(schedule);
        }

        return "redirect:/doctors/MedecinFiche";
    }




    // Process the form to update a doctor
    @PostMapping("/edit/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute DoctorModel doctor) {
        doctorService.saveDoctor(doctor); // Using save for both add and update operations
        return "redirect:/doctors";
    }

    // Display form to edit a doctor's schedule
    @GetMapping("/{doctorId}/schedule/edit/{scheduleId}")
    public String showEditScheduleForm(@PathVariable Long doctorId, @PathVariable Long scheduleId, Model model) {
        ScheduleModel schedule = doctorService.getScheduleById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));
        model.addAttribute("schedule", schedule);
        return "doctor/editSchedule";
    }

    // Process the form to edit a schedule
    @PostMapping("/{doctorId}/schedule/edit/{scheduleId}")
    public String updateSchedule(@PathVariable Long doctorId, @ModelAttribute ScheduleModel schedule) {
        doctorService.editSchedule(schedule);
        return "redirect:/doctors/" + doctorId + "/schedule";
    }

    private ScheduleModel extractScheduleForDay(Map<String, String> params, DayOfWeek day, DoctorModel doctor) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String openTimeKey = day.toString().toLowerCase() + "Start";
        String closeTimeKey = day.toString().toLowerCase() + "End";

        String openTimeString = params.getOrDefault(openTimeKey, "");
        String closeTimeString = params.getOrDefault(closeTimeKey, "");

        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        if (!openTimeString.isEmpty()) {
            startTime = LocalDateTime.parse(openTimeString, timeFormatter);
        }
        if (!closeTimeString.isEmpty()) {
            endTime = LocalDateTime.parse(closeTimeString, timeFormatter);
        }

        return new ScheduleModel(/* id, doctor, startTime, endTime, day, status, type */);
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
            for (ScheduleModel schedule : doctorModel.getSchedules()) {
                String day = schedule.getDayOfWeek().toString().toLowerCase();
                model.addAttribute(day + "Start", schedule.getStartTime().toLocalTime());
                model.addAttribute(day + "End", schedule.getEndTime().toLocalTime());
            }
            model.addAttribute("doctor", doctorModel);
        }
        return "MedecinFiche";
    }
    
}
