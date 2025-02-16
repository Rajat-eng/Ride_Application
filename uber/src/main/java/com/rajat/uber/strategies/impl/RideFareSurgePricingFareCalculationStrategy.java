package com.rajat.uber.strategies.impl;

import org.springframework.stereotype.Service;
import com.rajat.uber.entities.RideRequest;
import com.rajat.uber.services.DistanceService;
import com.rajat.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;


    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;
    }
}
