package com.rajat.uber.strategies;

import com.rajat.uber.dto.RideRequestDto;

public interface RideFareCalculatingStrategy {
    double calculateFare(RideRequestDto rideRequestDto);

}
