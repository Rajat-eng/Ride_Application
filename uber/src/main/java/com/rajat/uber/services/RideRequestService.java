package com.rajat.uber.services;

import com.rajat.uber.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
