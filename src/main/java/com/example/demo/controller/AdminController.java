package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.AbstractDocument;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

   private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users=adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
}
