/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.ClinicModel;
import com.ProjetFinal.CarolineSDianaF.Models.DoctorModel;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diana
 */
public interface DoctorService {

    // Méthode par le JPARepository qui ajoute et update
    DoctorModel save(DoctorModel doctor);


}
