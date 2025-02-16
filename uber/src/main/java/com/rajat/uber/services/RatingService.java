package com.rajat.uber.services;

import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.RiderDto;
import com.rajat.uber.entities.Ride;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);

    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
