package com.rajat.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rajat.uber.entities.RideRequest;


@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {


}
