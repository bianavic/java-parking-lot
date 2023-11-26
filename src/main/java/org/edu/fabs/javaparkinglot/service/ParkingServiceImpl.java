package org.edu.fabs.javaparkinglot.service;

import org.edu.fabs.javaparkinglot.domain.Parking;
import org.edu.fabs.javaparkinglot.exception.ParkingNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ParkingServiceImpl implements ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id1 = getUUID();
        var id2 = getUUID();
        Parking parking1 = new Parking(id1, "VWG-2222", "MG", "VW GOL", "BLACK");
        Parking parking2 = new Parking(id2, "SUB-1111", "SP", "SUBARU VIVIO", "GREEN");
        parkingMap.put(id1, parking1);
        parkingMap.put(id2, parking2);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }

    @Override
    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    @Override
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

}
