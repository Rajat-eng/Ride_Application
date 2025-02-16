package com.rajat.uber.strategies.impl;

import org.springframework.stereotype.Service;
import com.rajat.uber.entities.RideRequest;
import com.rajat.uber.services.DistanceService;
import com.rajat.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER;
    }
}
