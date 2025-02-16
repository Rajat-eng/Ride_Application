package com.rajat.uber.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.RideDto;
import com.rajat.uber.dto.RideRequestDto;
import com.rajat.uber.dto.RiderDto;
import com.rajat.uber.dto.UserDto;
import com.rajat.uber.repositories.RideRequestRepository;
import com.rajat.uber.repositories.RiderRepository;
import com.rajat.uber.services.RiderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RiderRepository riderRepository;
    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        return null;
    }

    @Override
    public RiderDto createNewRider(UserDto userDto) {
        return null;
    }

    @Override
    public RiderDto getCurrentRider() {
        return null;
    }
}
