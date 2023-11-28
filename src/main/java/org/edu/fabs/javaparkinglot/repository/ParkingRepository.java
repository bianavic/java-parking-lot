package org.edu.fabs.javaparkinglot.repository;

import org.edu.fabs.javaparkinglot.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, String> {
}
