package com.example.demo.controller;

import com.example.demo.Dto.AppUserDto;
import com.example.demo.Dto.AuthDto;
import com.example.demo.Dto.AuthResponseDto;
import com.example.demo.model.AppUser;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(
            @RequestBody AppUserDto appUserDto) {
        AuthResponseDto response = authService.registerUser(appUserDto);

        if ("success".equals(response.getMessage())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthDto authDto) {
        AuthResponseDto response = authService.loginUser(authDto);
        if ("success".equals(response.getMessage())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }

    }
}
