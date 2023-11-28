package org.edu.fabs.javaparkinglot.auth.controller;

import org.edu.fabs.javaparkinglot.auth.dto.LoginResponse;
import org.edu.fabs.javaparkinglot.auth.dto.LoginUserDto;
import org.edu.fabs.javaparkinglot.auth.dto.RegisterUserDto;
import org.edu.fabs.javaparkinglot.auth.entity.User;
import org.edu.fabs.javaparkinglot.auth.service.AuthenticationService;
import org.edu.fabs.javaparkinglot.auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
