package com.rajat.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.Ride;
import com.rajat.uber.entities.RideRequest;
import com.rajat.uber.entities.Rider;
import com.rajat.uber.entities.enums.RideStatus;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequestDto, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

}
