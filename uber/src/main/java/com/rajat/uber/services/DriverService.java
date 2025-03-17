package com.rajat.uber.services;

import com.rajat.uber.entities.Driver;

import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.PointDto;
import com.rajat.uber.dto.RideDto;
import com.rajat.uber.dto.RiderDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
    DriverDto updateLocation(PointDto currentLocation);

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver driver);
}
