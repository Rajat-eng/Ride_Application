package com.rajat.uber.services;

import java.util.List;
import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.RideDto;
import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.dto.RiderDto;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();
}
