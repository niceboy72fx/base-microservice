package com.microservice.authentication_service.service.implement;

import com.microservice.authentication_service.common.TokenType;
import com.microservice.authentication_service.controller.request.LoginRequest;
import com.microservice.authentication_service.controller.response.TokenResponse;
import com.microservice.authentication_service.exception.InvalidDataException;
import com.microservice.authentication_service.model.RedisToken;
import com.microservice.authentication_service.repositories.TokenRepository;
import com.microservice.authentication_service.repositories.UserRepository;
import com.microservice.authentication_service.service.AuthenticationService;
import com.microservice.authentication_service.service.JwtService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpHeaders.REFERER;

@Service
public class AuthenServiceImplement implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public TokenResponse createAccessToken(LoginRequest request) {
        var user = userRepository.findByUsername(request.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword(), user.getAuthorities()));
        String accessToken = jwtService.generateToken(user.getId(), user.getUsername(), user.getAuthorities());

        String refreshToken = jwtService.generateRefreshToken(user.getId(), user.getUsername(), user.getAuthorities());

        List<String> roleList = user.getRoles().stream().map(role -> role.getRoles().getName()).toList();
        tokenRepository.save(RedisToken.builder().id(request.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .flatForm(request.getPlatform().getPlatformName())
                .deviceToken(request.getDeviceToken())
                .roles(roleList.toString()).build());

        return TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    @Override
    public TokenResponse createRefreshToken(HttpServletRequest request) {
        final String refreshToken = request.getHeader(REFERER);

        if (StringUtils.isBlank(refreshToken)) {
            throw new InvalidDataException("Token must be not blank");
        }

        final String userName = jwtService.extractUsername(refreshToken, TokenType.REFRESH_TOKEN);
        var user = userRepository.findByUsername(userName);

        if (!jwtService.isValid(refreshToken, TokenType.REFRESH_TOKEN, user.getUsername())) {
            throw new InvalidDataException("Not allow access with this token");
        }

        String accessToken = jwtService.generateToken(user.getId(), user.getUsername(), user.getAuthorities());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
