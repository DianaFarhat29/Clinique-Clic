package com.ProjetFinal.CarolineSDianaF.Configuration;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomFailureHandler  implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // Ajouter le message d'erreur dans une session ou une requête
        request.getSession().setAttribute("errorMsg", "*Vos identifiants de connexion sont incorrects Veuillez réessayer.");

        // Rediriger vers la même page de login
        response.sendRedirect("/login");
    }
}
