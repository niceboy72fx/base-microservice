package com.microservice.authentication_service.controller;

import com.microservice.authentication_service.controller.request.LoginRequest;
import com.microservice.authentication_service.controller.request.RegisterRequest;
import com.microservice.authentication_service.controller.response.TokenResponse;
import com.microservice.authentication_service.exception.ErrorResponse;
import com.microservice.authentication_service.exception.GlobalException;
import com.microservice.authentication_service.exception.InvalidDataException;
import com.microservice.authentication_service.model.User;
import com.microservice.authentication_service.repositories.UserRepository;
import com.microservice.authentication_service.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@RestController
@Slf4j
public record AuthenticationController (AuthenticationService authenticationService, UserRepository userRepository /**function want to inject **/ ) { // using record instead class's injection

    @Operation(summary = "Login", description = "Login")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return new ResponseEntity<>(authenticationService.createAccessToken(request), HttpStatus.OK);
        } catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.CONFLICT.value(),
                    "Password or Username not match !",
                    "/authen/login",
                    "Password or Username not match !"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Register", description = "Generate access token")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser != null) {
            ErrorResponse errorResponse = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.CONFLICT.value(),
                    "Account already exists!",
                    "/authen/register",
                    "Account with username '" + request.getUsername() + "' already exists."
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        TokenResponse tokenResponse = authenticationService.createNewUser(request);
        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Refresh Token", description = "Generate refresh token")
    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenResponse> refreshToken(HttpServletRequest request) {
        return new ResponseEntity<>(authenticationService.createRefreshToken(request), HttpStatus.OK);
    }


}
