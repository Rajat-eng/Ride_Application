package com.rajat.uber.dto;

import java.time.LocalDateTime;
import org.locationtech.jts.geom.Point;
import com.rajat.uber.entities.enums.PaymentMethod;
import com.rajat.uber.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;

    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
