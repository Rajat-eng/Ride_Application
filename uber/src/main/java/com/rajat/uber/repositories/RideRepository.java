package com.rajat.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajat.uber.entities.Ride;

public interface RideRepository extends JpaRepository<Ride, Long>{

}
