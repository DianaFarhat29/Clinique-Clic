package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.ClinicService;
import com.ProjetFinal.CarolineSDianaF.Models.*;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/CompteClinique")
public class ClinicRegistrationController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display the registration form
    @GetMapping
    public String showRegistrationForm(Model model) {
        ClinicModel clinic = new ClinicModel();
        clinic.setContactDetails(new ContactDetailsModel());
        model.addAttribute("clinic", clinic);
        return "CompteClinique";
    }

    // Process the registration form
    @PostMapping
    public String registerClinic(@ModelAttribute ClinicModel clinic,
                                 @RequestParam String password) {

        // The username is the professional number
        String email = clinic.getContactDetails().getEmail();

        // Create a new user with username and password
        UserModel newUser = new UserModel();
        newUser.setUsername(email);
        newUser.setRole(Role.CLINIC);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));

        // Save the user
        UserModel savedUser = userRepository.save(newUser);

        // Associate the user with the clinic
        clinic.setUser(savedUser);

        // Save the clinic
        clinicService.save(clinic);

        // Redirect to a confirmation page or login page
        return "redirect:/login";
    }
}
