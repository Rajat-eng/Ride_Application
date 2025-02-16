package com.rajat.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rajat.uber.entities.Rider;

public interface RiderRepository extends JpaRepository<Rider, Long> {

}
