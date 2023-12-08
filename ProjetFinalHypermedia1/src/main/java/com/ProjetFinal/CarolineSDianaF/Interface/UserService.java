package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.UserModel;

import java.util.Optional;

public interface UserService {
    UserModel registerUser(UserModel user);
    Optional<UserModel> findByUsername(String username);

}
