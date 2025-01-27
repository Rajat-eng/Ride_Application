package com.rajat.uber.services;

import java.util.List;
import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.RideDto;
import com.rajat.uber.dto.RiderDto;

public interface DriverService {
    RideDto accepRide(Long rideId);

    RideDto cencelRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();
}
