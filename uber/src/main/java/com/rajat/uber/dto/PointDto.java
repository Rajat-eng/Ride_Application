package com.rajat.uber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PointDto {
    private double[] coordinates;
    private String type = "Point";

    public PointDto(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
