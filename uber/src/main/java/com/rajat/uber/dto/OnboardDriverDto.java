package com.rajat.uber.dto;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardDriverDto {
    private String vehicleId;
    private Point currentLocation;
}