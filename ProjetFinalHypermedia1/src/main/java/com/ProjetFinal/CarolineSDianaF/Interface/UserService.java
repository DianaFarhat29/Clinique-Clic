package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    // Method for registering a user
    UserModel registerUser(UserModel user);

    // Method for finding a user by username
    Optional<UserModel> findByUsername(String username);

}
