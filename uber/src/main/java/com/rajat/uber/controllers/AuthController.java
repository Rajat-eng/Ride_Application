package com.rajat.uber.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.uber.services.AuthService;
import com.rajat.uber.dto.SignUpDto;
import com.rajat.uber.dto.UserDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    UserDto signUp (@RequestBody SignUpDto signUpDto) {
        
        return authService.signup(signUpDto);
    }
    
}
