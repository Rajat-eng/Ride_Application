package com.rajat.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.Ride;
import com.rajat.uber.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RideRepository extends JpaRepository<Ride, Long> {
    Page<Ride> findByRider(Rider rider, Pageable pageRequest);

    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);
}
