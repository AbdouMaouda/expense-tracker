package com.example.demo.service;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {
    List<AppUser> getAllUsers();
}
