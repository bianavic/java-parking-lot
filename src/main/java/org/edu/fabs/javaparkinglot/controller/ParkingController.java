package org.edu.fabs.javaparkinglot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.edu.fabs.javaparkinglot.controller.dto.ParkingCreateDTO;
import org.edu.fabs.javaparkinglot.controller.dto.ParkingDTO;
import org.edu.fabs.javaparkinglot.controller.mapper.ParkingMapper;
import org.edu.fabs.javaparkinglot.domain.Parking;
import org.edu.fabs.javaparkinglot.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Parking Controller", description = "The parking lot management api")
@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    @GetMapping
    @Operation(summary = "finding all parking's")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "finding a parking by its ID")
    public ResponseEntity<ParkingDTO> findByID(@PathVariable String id) {
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "create a parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deleting a parking by its ID")
    public ResponseEntity<ParkingDTO> deleteByID(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "create a parking")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.update(id, parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
        //TODO verificar se já não esta fechado e lançar exceção
        Parking parking = parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

}
