package com.rajat.uber.repositories;

import java.sql.Driver;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rajat.uber.entities.Rating;
import com.rajat.uber.entities.Ride;
import com.rajat.uber.entities.Rider;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);

    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
