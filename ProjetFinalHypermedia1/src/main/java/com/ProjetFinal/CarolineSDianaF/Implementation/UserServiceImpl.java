package com.ProjetFinal.CarolineSDianaF.Implementation;

import com.ProjetFinal.CarolineSDianaF.Interface.UserService;
import com.ProjetFinal.CarolineSDianaF.Models.UserModel;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel registerUser(UserModel user) {
        // Hasher le mot de passe avant de l'enregistrer
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Enregistrement de l'utilisateur dans la base de données
        return userRepository.save(user);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
