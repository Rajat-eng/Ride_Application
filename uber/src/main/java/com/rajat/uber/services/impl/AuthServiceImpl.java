package com.rajat.uber.services.impl;

import org.springframework.stereotype.Service;
import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.SignUpDto;
import com.rajat.uber.dto.UserDto;
import com.rajat.uber.entities.User;
import com.rajat.uber.entities.enums.Role;
import com.rajat.uber.exceptions.RuntimeConflictException;
import com.rajat.uber.repositories.UserRepository;
import com.rajat.uber.services.AuthService;
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
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignUpDto signupDto) {
        User user=userRepository.findByEmail(null).orElse(null);
        if(user!=null){
           throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail()); 
        }
        // dto to entity
        User mappedUser=modelMapper.map(signupDto,User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser=userRepository.save(mappedUser); 
        riderService.createNewRider(modelMapper.map(savedUser,UserDto.class));
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onBoardNewDriver(Long userId) {
        return null;
    }
}
