package com.rajat.uber.entities;

import com.rajat.uber.entities.enums.PaymentMethod;
import com.rajat.uber.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor  // ✅ Explicitly add a no-args constructor
@AllArgsConstructor  // ✅ Ensure full constructor exists for @Builder
@Table(name="ride_request",indexes={@Index(name="idx_rider_id",columnList="rider_id")})
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id", nullable = false) 
    private Rider rider;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;

    private Double fare;
}

