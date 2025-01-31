package com.rajat.uber.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rajat.uber.dto.RideDto;
import com.rajat.uber.dto.RideStartDto;
import com.rajat.uber.services.DriverService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestid}")
    public ResponseEntity<RideDto> acceptRide(@RequestBody Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }
    
    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId,
                                              @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));
    }
}
