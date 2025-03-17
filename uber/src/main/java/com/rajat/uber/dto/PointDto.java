package com.rajat.uber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PointDto {
    private double[] coordinates;
    private String type = "Point";

    public PointDto(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
