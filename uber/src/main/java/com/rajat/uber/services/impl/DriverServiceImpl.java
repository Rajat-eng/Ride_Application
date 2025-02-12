package com.rajat.uber.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.RideDto;
import com.rajat.uber.dto.RiderDto;
import com.rajat.uber.exceptions.ResourceNotFoundException;
import com.rajat.uber.repositories.DriverRepository;
import com.rajat.uber.services.DriverService;
import com.rajat.uber.services.RideService;
import com.rajat.uber.services.RideRequestService;

import lombok.RequiredArgsConstructor;

import java.sql.Driver;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    @Override
    public RideDto acceptRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId,String otp ) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found with " +
                "id "+2));
    }
}
