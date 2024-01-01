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

        // Apply capitalization to the clinic name
        clinic.setName(capitalizeFirstLetter(clinic.getName()));

        // Apply capitalization to the contact details
        ContactDetailsModel contactDetails = clinic.getContactDetails();
        if (contactDetails != null) {
            contactDetails.setPhoneNumber(capitalizeFirstLetter(contactDetails.getPhoneNumber()));
            contactDetails.setEmail(capitalizeFirstLetter(contactDetails.getEmail()));
            contactDetails.setRue(capitalizeFirstLetter(contactDetails.getRue()));
            contactDetails.setNoLocal(capitalizeFirstLetter(contactDetails.getNoLocal()));
            contactDetails.setVille(capitalizeFirstLetter(contactDetails.getVille()));
            contactDetails.setCodePostal(contactDetails.getCodePostal().toUpperCase());
        }

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

    // Capitalize the first letter of a string
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty() || !Character.isLetter(input.charAt(0))) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

}
