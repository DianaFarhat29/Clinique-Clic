package com.ProjetFinal.CarolineSDianaF.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    // Display the login form
    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request, Model model) {
        String errorMsg = (String) request.getSession().getAttribute("errorMsg");
        if (errorMsg != null) {
            model.addAttribute("errorMsg", errorMsg);
            request.getSession().removeAttribute("errorMsg");
        }
        return "login";
    }
}


