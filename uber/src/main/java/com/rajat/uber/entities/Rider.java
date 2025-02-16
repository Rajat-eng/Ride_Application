package com.rajat.uber.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {@Index(name = "idx_ride_request_rider", columnList = "rider_id")})
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // join with id in User table
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating = 0.0;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RideRequest> rideRequests; // One rider can have multiple ride requests

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Ride> rides = new ArrayList<>();

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Rating> ratings = new ArrayList<>();
}
