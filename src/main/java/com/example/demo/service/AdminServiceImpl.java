package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    private final AppUserRepository appUserRepository;

    public AdminServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }
}
