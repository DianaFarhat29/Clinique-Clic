package com.ProjetFinal.CarolineSDianaF.Configuration;

import com.ProjetFinal.CarolineSDianaF.Models.Role;
import com.ProjetFinal.CarolineSDianaF.Models.UserModel;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class AdminAccountSetup implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.username:admin}")
    private String adminUsername;

    @Value("${admin.password:}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        // Generate a random password if not set
        if (adminPassword == null || adminPassword.isEmpty()) {
            adminPassword = UUID.randomUUID().toString();
            System.out.println("Generated Admin Password: " + adminPassword);
        }

        // Check if the admin account already exists
        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            // Admin account does not exist, create one
            UserModel adminUser = new UserModel();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            adminUser.setEmail("admin@example.com");
            adminUser.setRole(Role.ADMIN);

            userRepository.save(adminUser);
        }
    }
}
