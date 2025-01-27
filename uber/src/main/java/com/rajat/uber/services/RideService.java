package com.rajat.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.Ride;
import com.rajat.uber.entities.enums.RideStatus;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);

}
