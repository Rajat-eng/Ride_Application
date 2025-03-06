package com.rajat.uber.services;

import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.SignUpDto;
import com.rajat.uber.dto.UserDto;

public interface AuthService {
    String[] login(String email, String password);

    UserDto signup(SignUpDto signUpDto);

    DriverDto onBoardNewDriver(Long userId,String vehicleId);

    String refreshToken(String refreshToken);
}
