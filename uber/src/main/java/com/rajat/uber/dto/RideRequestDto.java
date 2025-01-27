package com.rajat.uber.dto;

import java.time.LocalDateTime;
import org.locationtech.jts.geom.Point;
import com.rajat.uber.entities.enums.PaymentMethod;
import com.rajat.uber.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
    private Long id;

    private Point pickupLocation;

    private Point dropOffLocation;

    private LocalDateTime requestedTime;

    private RiderDto rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;
}
