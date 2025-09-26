package com.example.demo.service;

import com.example.demo.model.AppUser;

public interface UserService {

    AppUser saveUser( AppUser appUser);
    AppUser getUserByUsername(String username);
}
