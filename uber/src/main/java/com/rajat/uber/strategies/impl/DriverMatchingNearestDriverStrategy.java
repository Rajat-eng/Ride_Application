package com.rajat.uber.strategies.impl;

import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.strategies.DriverMatchingStrategy;

public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
