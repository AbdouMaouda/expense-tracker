package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));

        if (appUser.getRole() == null) {
            appUser.setRole(Role.USER);
            appUserRepository.save(appUser);
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name());


        System.out.println("Loaded user: " + appUser.getUsername() +
                " with role: " + appUser.getRole());


        return new User(appUser.getUsername(),
                appUser.getPassword(),
                Collections.singleton(authority));


    }

}
