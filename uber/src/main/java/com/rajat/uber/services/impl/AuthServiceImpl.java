package com.rajat.uber.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.SignUpDto;
import com.rajat.uber.dto.UserDto;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.User;
import com.rajat.uber.entities.enums.Role;
import com.rajat.uber.exceptions.ResourceNotFoundException;
import com.rajat.uber.exceptions.RuntimeConflictException;
import com.rajat.uber.repositories.UserRepository;
import com.rajat.uber.security.JWTService;
import com.rajat.uber.services.AuthService;
import com.rajat.uber.services.DriverService;
import com.rajat.uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import java.util.Set;
import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final DriverService driverService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new String[] {accessToken, refreshToken};
    }

    @Override
    public UserDto signup(SignUpDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeConflictException(
                    "Cannot signup, User already exists with email " + signupDto.getEmail());
        }
        // dto to entity
        User mappedUser = modelMapper.map(signupDto, User.class);
        // register a new rider
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        mappedUser.setRoles(Set.copyOf(signupDto.getRoles()));
        User savedUser = userRepository.save(mappedUser);
        riderService.createNewRider(savedUser);
        // entity to DTO
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onBoardNewDriver(Long userId, String vehicleId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id " + userId));
        if (user.getRoles().contains(Role.DRIVER)) {
            throw new RuntimeConflictException("User with id " + userId + " is already a Driver");
        }
        Driver createDriver = Driver.builder().user(user).rating(0.0).vehicleId(vehicleId)
                .available(true).build();
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDto.class);

    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found " + "with id: " + userId));

        return jwtService.generateAccessToken(user);
    }
}
