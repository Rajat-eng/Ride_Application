package com.rajat.uber.repositories;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajat.uber.entities.Driver;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    // Query to find the 10 nearest available drivers to a pickup location (within 10 km)
    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
                   "FROM driver d " +
                   "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
                   "ORDER BY distance ASC LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickupLocation);

    // Query to find the 10 nearest available top-rated drivers to a pickup location (within 15 km)
    @Query(value = "SELECT d.* " +
                   "FROM driver d " +
                   "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) " +
                   "ORDER BY d.rating DESC LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);
}
