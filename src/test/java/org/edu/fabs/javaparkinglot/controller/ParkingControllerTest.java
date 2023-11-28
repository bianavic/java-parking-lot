package org.edu.fabs.javaparkinglot.controller;

import io.restassured.RestAssured;
import org.edu.fabs.javaparkinglot.controller.dto.ParkingCreateDTO;
import org.edu.fabs.javaparkinglot.domain.Parking;
import org.edu.fabs.javaparkinglot.repository.ParkingRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerDatabase {

    @Autowired
    private ParkingRepository parkingRepository;

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    @DisplayName("should return 200 when get request is find all")
    void findAll() {
        RestAssured.when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("should return 200 when get request is find by ID")
    void findByID() {
        Parking parking = new Parking(
                "b732b1be529e4866a58e16576fe81544",
                "ABC 456",
                "PE",
                "Chevrolet Onix",
                "BLACK",
                LocalDateTime.now(), null, null);
        parkingRepository.save(parking);

        RestAssured
                .given()
                    .pathParam("id", parking.getId())
                .when()
                    .get("parking/{id}")
                    .then()
                    .statusCode(200)
                    .body("license", Matchers.equalTo("ABC 456"));
    }

    @Test
    @DisplayName("should return 201 when post request is create")
    void create() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("YELLOW");
        createDTO.setLicense("MQ-4443");
        createDTO.setModel("GOL");
        createDTO.setState("ES");

        RestAssured.given()
                .when()
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("MQ-4443"))
                .body("color", Matchers.equalTo("YELLOW"));
    }

    @Test
    @DisplayName("should return 204 when delete parking by its ID")
    void deleteByID() {
        Parking parking = new Parking(
                "b732b1be529e4866a58e16576fe81544",
                "ABC 456",
                "PE",
                "Chevrolet Onix",
                "BLACK",
                LocalDateTime.now(), null, null);
        parkingRepository.save(parking);

        RestAssured
                .given()
                .pathParam("id", parking.getId())
                .when()
                .delete("parking/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("should return 200 when update parking by its ID")
    void update() {
        Parking entity = new Parking(
                "b732b1be529e4866a58e16576fe81544",
                "ABC 456",
                "CE",
                "Chevrolet Onix",
                "BLACK",
                LocalDateTime.now(), null, null);
        parkingRepository.save(entity);

        ParkingCreateDTO request = new ParkingCreateDTO("ABC 456", "SP", "Chevrolet Onix", "RED");

        RestAssured
                .given()
                .header("Content-type", "application/json")
                .and()
                .body(request)
                .when()
                .put("parking/b732b1be529e4866a58e16576fe81544")
                .then()
                .statusCode(200)
                .body("state", Matchers.equalTo("SP"));
    }

    @Test
    @DisplayName("should return 200 when post request is exit by ID")
    void exit() {
        Parking created = new Parking(
                "b732b1be529e4866a58e16576fe81544",
                "ABC 456",
                "CE",
                "Chevrolet Onix",
                "BLACK",
                LocalDateTime.now(), null, null);
        parkingRepository.save(created);

        parkingRepository.findById(created.getId());

        LocalDateTime expectedExitDate = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedExpectedExitDate = dateFormat.format(expectedExitDate);

        RestAssured.given()
                .given()
                .pathParam("id", created.getId())
                .when()
                .post("/parking/{id}/exit")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("exitDate", Matchers.equalTo(formattedExpectedExitDate));
    }

}