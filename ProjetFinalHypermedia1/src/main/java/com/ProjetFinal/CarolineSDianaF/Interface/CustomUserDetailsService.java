package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CustomUserDetailsService extends UserDetailsService{


    // Method used by Spring Security during the authentication process. It retrieves the user's
    // information (such as username, password, and roles) from the data source (e.g., database) and returns a UserDetails object containing that information.
    @Override
    public UserDetails loadUserByUsername(String username);

    // Utility method to help implement loadUserByUsername(String username)
    Collection<? extends GrantedAuthority> getAuthorities(UserModel user);


}
