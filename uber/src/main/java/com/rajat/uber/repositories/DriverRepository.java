package com.rajat.uber.repositories;

import com.rajat.uber.entities.Driver;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.locationtech.jts.geom.Point;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
        @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance "
                        + "FROM driver d "
                        + "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) "
                        + "ORDER BY distance " + "LIMIT 10")
        List<Driver> findTenNearestDrivers(Point pickupLocation);

        @Query(value = "SELECT d.* " + "FROM driver d "
                        + "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) "
                        + "ORDER BY d.rating DESC " + "LIMIT 10")
        List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);
}
