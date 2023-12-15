package com.ProjetFinal.CarolineSDianaF.Controllers;

import com.ProjetFinal.CarolineSDianaF.Interface.DoctorService;
import com.ProjetFinal.CarolineSDianaF.Models.ContactDetailsModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import com.ProjetFinal.CarolineSDianaF.Models.Role;
import com.ProjetFinal.CarolineSDianaF.Models.UserModel;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/CompteMedecin")
public class DoctorRegistrationController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display the registration form
    @GetMapping
    public String showRegistrationForm(Model model) {
        DoctorModel doctor = new DoctorModel();
        doctor.setContactDetails(new ContactDetailsModel());
        model.addAttribute("doctor", doctor);
        return "CompteMedecin";
    }

    // Process the registration form
    @PostMapping
    public String registerDoctor(@ModelAttribute DoctorModel doctor,
                                 @RequestParam String password) {

        // The username is the professional number
        String username = doctor.getProfessionalNumber().toString();
        String email = doctor.getContactDetails().getEmail();

        // Create a new user with username and password
        UserModel newUser = new UserModel();
        newUser.setUsername(username);
        newUser.setRole(Role.DOCTOR);
        newUser.setEmail(email);
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
