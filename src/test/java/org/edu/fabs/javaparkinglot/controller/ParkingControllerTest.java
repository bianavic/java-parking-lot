package org.edu.fabs.javaparkinglot.controller;

import io.restassured.RestAssured;
import org.edu.fabs.javaparkinglot.controller.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

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
                .statusCode(HttpStatus.OK.value())
                .body("license[0]", Matchers.equalTo("VWG-2222"));
    }

//    @Test
//    void findByID() {
//    }

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

//    @Test
//    void deleteByID() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void exit() {
//    }
}