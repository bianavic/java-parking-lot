package org.edu.fabs.javaparkinglot.service;

import lombok.RequiredArgsConstructor;
import org.edu.fabs.javaparkinglot.domain.Parking;
import org.edu.fabs.javaparkinglot.exception.ParkingNotFoundException;
import org.edu.fabs.javaparkinglot.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;

    @Override
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Override
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Override
    public void delete(String id) {
        parkingRepository.deleteById(id);
    }

    @Override
    public Parking update(String id, Parking parkingCreate) {
        Parking parkingByID = findById(id);
        parkingByID.setColor(parkingCreate.getColor());
        parkingRepository.save(parkingByID);
        return parkingByID;
    }

    @Override
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        return parkingRepository.save(parking);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
