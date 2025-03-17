package com.rajat.uber.services.impl;

import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.PointDto;
import com.rajat.uber.dto.RideDto;
import com.rajat.uber.dto.RiderDto;
import com.rajat.uber.exceptions.ResourceNotFoundException;
import com.rajat.uber.repositories.DriverRepository;
import com.rajat.uber.services.DriverService;
import com.rajat.uber.services.PaymentService;
import com.rajat.uber.services.RatingService;
import com.rajat.uber.services.RideService;

import jakarta.transaction.Transactional;

import com.rajat.uber.services.RideRequestService;

import lombok.RequiredArgsConstructor;

import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.Ride;
import com.rajat.uber.entities.RideRequest;
import com.rajat.uber.entities.User;
import com.rajat.uber.entities.enums.RideRequestStatus;
import com.rajat.uber.entities.enums.RideStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final PaymentService paymentService;
    private final RatingService ratingService;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto updateLocation(PointDto currentLocation){
        Driver driver= getCurrentDriver();
        Point point=modelMapper.map(currentLocation,Point.class);
        System.out.println("current location " + point);
        driver.setCurrentLocation(point);
        driverRepository.save(driver);
        return modelMapper.map(driver,DriverDto.class);
    }
    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest= rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("RideRequest cannot be accepted, status is "+ rideRequest.getRideRequestStatus());
        }
        Driver currentDriver=getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }
        currentDriver.setAvailable(false);
        Driver savedDriver=driverRepository.save(currentDriver);
        Ride ride=rideService.createNewRide(rideRequest,savedDriver);
        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver cannot cancel this ride as he has not accepted it earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled, invalid status: "+ride.getRideStatus());
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver, true);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto startRide(Long rideId, String otp) {
        Ride ride=rideService.getRideById(rideId);
        Driver driver= getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride status is not CONFIRMED hence cannot be started, status: "+ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())) {
            throw new RuntimeException("Otp is not valid, otp: "+otp);
        }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        paymentService.createNewPayment(savedRide);
        ratingService.createNewRating(savedRide);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.ONGOING)) {
            throw new RuntimeException("Ride status is not ONGOING hence cannot be ended, status: "+ride.getRideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        paymentService.processPayment(ride);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver is not the owner of this Ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException("Ride status is not Ended hence cannot start rating, status: "+ride.getRideStatus());
        }

        return ratingService.rateRider(ride, rating);
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        Page<Ride> ridesPage = rideService.getAllRidesOfDriver(currentDriver, pageRequest);
        return ridesPage.map(ride -> modelMapper.map(ride,RideDto.class));
    }

    @Override
    public Driver getCurrentDriver() {
         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return driverRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not associated with user with " +
                "id "+user.getId()));
    }
    

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
