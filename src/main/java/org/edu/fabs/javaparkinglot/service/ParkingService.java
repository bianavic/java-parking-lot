package org.edu.fabs.javaparkinglot.service;

import org.edu.fabs.javaparkinglot.domain.Parking;

import java.util.List;

public interface ParkingService {

    List<Parking> findAll();

}
