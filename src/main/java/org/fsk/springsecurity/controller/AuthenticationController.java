package org.fsk.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.fsk.springsecurity.dto.LoginResponse;
import org.fsk.springsecurity.dto.RegisterUserDTO;
import org.fsk.springsecurity.entities.User;
import org.fsk.springsecurity.service.AuthenticationService;
import org.fsk.springsecurity.service.JWTService;
import org.fsk.springsecurity.dto.LoginUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController @RequiredArgsConstructor
public class AuthenticationController {

    private final JWTService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
