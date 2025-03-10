package com.rajat.uber.strategies.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rajat.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.RideRequest;
import com.rajat.uber.repositories.DriverRepository;


@Service
@RequiredArgsConstructor
@Transactional

public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
