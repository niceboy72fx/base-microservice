package com.microservice.authentication_service.service;


import com.microservice.authentication_service.controller.request.LoginRequest;
import com.microservice.authentication_service.controller.response.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    TokenResponse createAccessToken(LoginRequest request);

    TokenResponse createRefreshToken(HttpServletRequest request);
}
