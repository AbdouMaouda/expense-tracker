package com.example.demo.service;

import com.example.demo.Dto.AppUserDto;
import com.example.demo.Dto.AuthDto;
import com.example.demo.Dto.AuthResponseDto;
import com.example.demo.model.AppUser;
import com.example.demo.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImplementation(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public AuthResponseDto registerUser(AppUserDto appUserDto) {
        if (userService.getUserByUsername(appUserDto.getUsername()) != null) {
            return new AuthResponseDto(null, "UserName Already Exists");
        }
        AppUser appUser = new AppUser();
        appUser.setFullName(appUserDto.getFullName());
        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));

        userService.saveUser(appUser);

        String token = jwtUtil.generateToken(appUserDto.getUsername());
        return new AuthResponseDto(token, "success");
    }

    @Override
    public AuthResponseDto loginUser(AuthDto authdto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authdto.getUsername(),
                    authdto.getPassword())
            );
            final String token = jwtUtil.generateToken(authdto.getUsername());

            return new AuthResponseDto(token, "success");
        } catch (BadCredentialsException e) {
            return new AuthResponseDto(null, "Invalid Credentials");
        }
    }
}
