/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetFinal.CarolineSDianaF.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Diana
 */
@Controller
public class ControllerIndex {

    // Display the view of the index
    @GetMapping("/Index")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to our website!");
        return "Index";
    }


}