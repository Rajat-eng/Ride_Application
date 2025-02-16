package com.rajat.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rajat.uber.entities.RideRequest;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {


}
