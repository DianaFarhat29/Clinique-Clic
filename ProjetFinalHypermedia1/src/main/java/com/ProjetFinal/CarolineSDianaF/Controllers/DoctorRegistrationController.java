package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.DoctorService;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.UserModel;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register/doctor")
public class DoctorRegistrationController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display the registration form
    @GetMapping
    public String showRegistrationForm() {
        // Return the view for registration
        return "doctorRegistration";
    }

    // Process the registration form
    @PostMapping
    public String registerDoctor(@ModelAttribute DoctorModel doctor,
                                 @RequestParam String username,
                                 @RequestParam String password) {
        // Create a new user with username and password
        UserModel newUser = new UserModel();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));

        // Save the user
        UserModel savedUser = userRepository.save(newUser);

        // Associate the user with the doctor
        doctor.setUser(savedUser);

        // Save the doctor
        doctorService.saveDoctor(doctor);

        // Redirect to a confirmation page or login page
        return "redirect:/login";
    }
}
