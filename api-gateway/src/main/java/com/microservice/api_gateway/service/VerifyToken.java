package com.microservice.api_gateway.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class VerifyToken {
    @GrpcClient("verify-token-service")
    private VerifyTokenServiceGrpc.VerifyTokenServiceBlockingStub verifyTokenServiceBlockingStub;

    public VerifyResponse verifyToken(String token) {
        log.info("-----[ verifyToken ]-----");
        VerifyRequest verifyRequest = VerifyRequest.newBuilder().setToken(token).build();
        VerifyResponse response = verifyTokenServiceBlockingStub.verifyAccessToken(verifyRequest);

        return response;
    }
}
