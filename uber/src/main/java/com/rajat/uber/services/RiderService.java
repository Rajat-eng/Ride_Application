package com.rajat.uber.services;

import java.util.List;
import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.RideDto;
import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.dto.RiderDto;
import com.rajat.uber.dto.UserDto;
import com.rajat.uber.entities.Rider;
import com.rajat.uber.entities.User;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    RiderDto createNewRider(UserDto userDto);

    RiderDto getCurrentRider();
}
