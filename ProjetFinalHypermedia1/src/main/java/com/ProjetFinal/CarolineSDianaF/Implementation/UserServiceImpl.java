package com.ProjetFinal.CarolineSDianaF.Implementation;

import com.ProjetFinal.CarolineSDianaF.Interface.UserService;
import com.ProjetFinal.CarolineSDianaF.Models.UserModel;
import com.ProjetFinal.CarolineSDianaF.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        // Hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        return userRepository.save(user);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            // Construction objet UserDetails avec les détails de l'utilisateur
            return User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().toString())
                    .build();
        } else {
            throw new UsernameNotFoundException("Utilisateur non trouvé: " + username);
        }
    }

}
