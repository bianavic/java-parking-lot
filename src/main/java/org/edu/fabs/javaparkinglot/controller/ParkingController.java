package org.edu.fabs.javaparkinglot.controller;

import lombok.RequiredArgsConstructor;
import org.edu.fabs.javaparkinglot.controller.dto.ParkingDTO;
import org.edu.fabs.javaparkinglot.controller.mapper.ParkingMapper;
import org.edu.fabs.javaparkinglot.domain.Parking;
import org.edu.fabs.javaparkinglot.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    @GetMapping
    public List<ParkingDTO> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return result;
    }

}
