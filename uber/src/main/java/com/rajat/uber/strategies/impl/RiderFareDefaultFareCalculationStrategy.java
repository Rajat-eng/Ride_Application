package com.rajat.uber.strategies.impl;

import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.strategies.RideFareCalculationStrategy;

public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
