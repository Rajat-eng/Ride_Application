package com.rajat.uber.services.impl;

import org.springframework.stereotype.Service;
import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.SignUpDto;
import com.rajat.uber.dto.UserDto;
import com.rajat.uber.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignUpDto signupDto) {
        return null;
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
