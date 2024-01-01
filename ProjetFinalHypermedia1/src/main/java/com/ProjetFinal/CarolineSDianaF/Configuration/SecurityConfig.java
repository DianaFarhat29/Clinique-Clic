package com.ProjetFinal.CarolineSDianaF.Configuration;

import com.ProjetFinal.CarolineSDianaF.Implementation.CustomUserDetailsServiceImpl;
import com.ProjetFinal.CarolineSDianaF.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.web.firewall.HttpFirewall;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity(debug = true)
@ComponentScan("com.ProjetFinal.CarolineSDianaF.Configuration")
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Filter Chain for Spring Security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
               // .csrf().disable()
                .csrf(csrf -> csrf.disable())

                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/doctors/**").hasRole("DOCTOR")
                                .requestMatchers("/patients/**").hasRole("PATIENT")
                                .requestMatchers("/clinics/**").hasRole("CLINIC")
                                .requestMatchers("/patients/getAvailableSlots/**").hasRole("PATIENT")
                                .requestMatchers(HttpMethod.POST, "/patients/bookAppointment").permitAll()
                                .requestMatchers("/login").anonymous()
                                .requestMatchers("/Index", "/", "/login","/MedecinFiche", "/CompteMedecin", "/CompteClinique", "/ComptePatient", "/Index", "/css/**", "/js/**", "/images/**").permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                                .successHandler(customSuccessHandler)
                                .failureHandler(new CustomFailureHandler())
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/Index?logout=true")
                                .deleteCookies("JSESSIONID")
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
