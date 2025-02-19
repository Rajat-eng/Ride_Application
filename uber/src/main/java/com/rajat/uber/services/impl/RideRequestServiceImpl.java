package com.rajat.uber.services.impl;

import org.springframework.stereotype.Service;

import com.rajat.uber.entities.RideRequest;
import com.rajat.uber.exceptions.ResourceNotFoundException;
import com.rajat.uber.repositories.RideRequestRepository;
import com.rajat.uber.services.RideRequestService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepository rideRequestRepository;


    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: "+rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: "+rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
