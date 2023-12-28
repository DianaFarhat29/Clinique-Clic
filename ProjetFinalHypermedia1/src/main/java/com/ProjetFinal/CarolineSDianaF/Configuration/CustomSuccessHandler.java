package com.ProjetFinal.CarolineSDianaF.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_DOCTOR")) {
            setDefaultTargetUrl("/doctors/MedecinFiche");
        } else if (roles.contains("ROLE_CLINIC")) {
            setDefaultTargetUrl("/clinics/CliniqueFiche");
        } else if (roles.contains("ROLE_PATIENT")) {
            setDefaultTargetUrl("/patients/PatientFiche");
        } else if (roles.contains("ROLE_ADMIN")) {
            setDefaultTargetUrl("/AdminViewsPatient");
        } else {
            setDefaultTargetUrl("/Index?error");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
