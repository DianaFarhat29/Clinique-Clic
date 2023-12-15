package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.PatientService;
import com.ProjetFinal.CarolineSDianaF.Models.*;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ComptePatient")
public class PatientRegistrationController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display the registration form
    @GetMapping
    public String showRegistrationForm(Model model) {
        PatientModel patient = new PatientModel();
        patient.setContactDetails(new ContactDetailsModel());
        model.addAttribute("patient", patient);
        return "ComptePatient";
    }

    // Process the form to set up password for a new patient
    @PostMapping
    public String registerPatient(@ModelAttribute PatientModel patient,
                                  @RequestParam String password) {

        // The username is the Health Insurance Number
        String username = patient.getHealthInsuranceNumber();
        String email = patient.getContactDetails().getEmail();

        // Create a new user with username and password
        UserModel newUser = new UserModel();
        newUser.setUsername(username);
        newUser.setRole(Role.PATIENT);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));

        // Save the user
        UserModel savedUser = userRepository.save(newUser);

        // Associate the user with the patient
        patient.setUser(savedUser);

        // Save the patient
        patientService.save(patient);

        // Redirect to a confirmation page or login page
        return "redirect:/login";
    }
}
