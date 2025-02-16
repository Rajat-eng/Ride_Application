package com.rajat.uber.strategies.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.RideRequest;
import com.rajat.uber.repositories.DriverRepository;
import com.rajat.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
