package com.rajat.uber.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.rajat.uber.dto.*;
import com.rajat.uber.entities.Rider;
import com.rajat.uber.entities.User;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest PageRequest);

    Rider createNewRider(User userDto);

    Rider getCurrentRider();
}
