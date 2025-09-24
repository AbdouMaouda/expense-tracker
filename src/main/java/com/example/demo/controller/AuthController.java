package com.example.demo.controller;

import com.example.demo.Dto.AppUserDto;
import com.example.demo.Dto.AuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody AppUserDto appUserDto) {
return ResponseEntity.ok
        ("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
        return ResponseEntity.ok
                ("Login successful");
    }
}
