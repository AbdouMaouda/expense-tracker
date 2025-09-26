package com.example.demo.service;

import com.example.demo.Dto.AppUserDto;
import com.example.demo.Dto.AuthDto;
import com.example.demo.Dto.AuthResponseDto;

public interface AuthService {

    AuthResponseDto registerUser(AppUserDto appUserDto);
    AuthResponseDto loginUser(AuthDto authdto);
}
