package com.rajat.uber.strategies;


import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.RideRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequestDto);
}
