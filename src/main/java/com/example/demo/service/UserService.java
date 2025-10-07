package com.example.demo.service;

import com.example.demo.model.AppUser;

import java.util.Optional;

public interface UserService {

    AppUser saveUser( AppUser appUser);
    AppUser getUserByUsername(String username);
    Optional<AppUser> findUserById(Long id);
}
