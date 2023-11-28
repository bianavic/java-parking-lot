package org.edu.fabs.javaparkinglot.service;

import lombok.RequiredArgsConstructor;
import org.edu.fabs.javaparkinglot.domain.Parking;
import org.edu.fabs.javaparkinglot.exception.ParkingNotFoundException;
import org.edu.fabs.javaparkinglot.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Override
    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Override
    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parkingByID = findById(id);
        parkingByID.setLicense(parkingCreate.getLicense());
        parkingByID.setState(parkingCreate.getState());
        parkingByID.setModel(parkingCreate.getModel());
        parkingByID.setColor(parkingCreate.getColor());
        parkingRepository.save(parkingByID);
        return parkingByID;
    }

    @Override
    @Transactional
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
