package com.ProjetFinal.CarolineSDianaF.Controllers;


import com.ProjetFinal.CarolineSDianaF.Interface.DoctorService;
import com.ProjetFinal.CarolineSDianaF.Interface.ClinicService;
import com.ProjetFinal.CarolineSDianaF.Models.*;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Optional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/CompteMedecin")
public class DoctorRegistrationController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display the registration form
    @GetMapping
    public String showRegistrationForm(Model model) {
        List<ClinicModel> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        DoctorModel doctor = new DoctorModel();
        doctor.setContactDetails(new ContactDetailsModel());
        model.addAttribute("doctor", doctor);
        return "CompteMedecin";
    }

    // Process the registration form
    @PostMapping
    public String registerDoctor(@ModelAttribute DoctorModel doctor,
                                 @RequestParam List<Long> clinicIds,
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

        // Récupération des cliniques et association avec le médecin
        Set<ClinicModel> selectedClinics = clinicIds.stream()
                .map(clinicService::getClinicById)
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());

        doctor.setClinics(selectedClinics);

        doctor.setFirstName(capitalizeFirstLetter(doctor.getFirstName()));
        doctor.setLastName(capitalizeFirstLetter(doctor.getLastName()));
        doctor.setSpeciality(capitalizeFirstLetter(doctor.getSpeciality()));

        ContactDetailsModel contactDetails = doctor.getContactDetails();
        if (contactDetails != null) {
            contactDetails.setPhoneNumber(capitalizeFirstLetter(contactDetails.getPhoneNumber()));
            contactDetails.setEmail(capitalizeFirstLetter(contactDetails.getEmail()));
            contactDetails.setRue(capitalizeFirstLetter(contactDetails.getRue()));
            contactDetails.setNoLocal(capitalizeFirstLetter(contactDetails.getNoLocal()));
            contactDetails.setVille(capitalizeFirstLetter(contactDetails.getVille()));
            contactDetails.setCodePostal(contactDetails.getCodePostal().toUpperCase());
        }

        // Save the user
        UserModel savedUser = userRepository.save(newUser);

        // Associate the user with the doctor
        doctor.setUser(savedUser);

        // Save the doctor
        doctorService.saveDoctor(doctor);

        // Redirect to a confirmation page or login page
        return "redirect:/login";
    }

    // Method to capitalize the first letter of a string
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty() || !Character.isLetter(input.charAt(0))) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

}
