package com.rajat.uber.strategies;

import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.entities.Driver;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);
}
