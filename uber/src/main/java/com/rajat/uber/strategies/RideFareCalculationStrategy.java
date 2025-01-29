package com.rajat.uber.strategies;

import com.rajat.uber.dto.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);

}
