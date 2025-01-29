package com.rajat.uber.strategies.impl;

import java.util.List;

import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.strategies.DriverMatchingStrategy;

public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
