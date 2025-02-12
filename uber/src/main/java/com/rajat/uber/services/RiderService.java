package com.rajat.uber.services;

import java.util.List;
import com.rajat.uber.dto.*;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    RiderDto createNewRider(UserDto userDto);

    RiderDto getCurrentRider();
}
