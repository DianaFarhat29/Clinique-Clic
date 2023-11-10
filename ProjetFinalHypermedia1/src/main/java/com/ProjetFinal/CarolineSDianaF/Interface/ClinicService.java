/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diana
 */
public interface ClinicService {
    // Method to add or upgrade
    ClinicModel save(ClinicModel clinic);
}
