package com.rajat.uber.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rajat.uber.entities.User;
import com.rajat.uber.exceptions.ResourceNotFoundException;
import com.rajat.uber.repositories.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return (UserDetails) userRepository.findByEmail(username).orElse(null);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+id));
    }
}
