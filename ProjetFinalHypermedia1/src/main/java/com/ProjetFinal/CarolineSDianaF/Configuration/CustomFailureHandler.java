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
        // Add an error message in the session
        request.getSession().setAttribute("errorMsg", "*Vos identifiants de connexion sont incorrects Veuillez réessayer.");

        // Redirect to login page
        response.sendRedirect("/login");
    }
}
