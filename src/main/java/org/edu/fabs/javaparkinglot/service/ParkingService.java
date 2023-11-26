package org.edu.fabs.javaparkinglot.service;

import org.edu.fabs.javaparkinglot.domain.Parking;

import java.util.List;

public interface ParkingService {

    List<Parking> findAll();

    Parking findById(String id);

    Parking create(Parking parkingCreate);

}
