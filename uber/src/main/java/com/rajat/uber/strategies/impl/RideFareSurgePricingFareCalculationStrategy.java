package com.rajat.uber.strategies.impl;

import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.strategies.RideFareCalculatingStrategy;

public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculatingStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
