package com.microservice.api_gateway.service;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import com.microservice.grpcserver.VerifyRequest;
import com.microservice.grpcserver.VerifyResponse;
import com.microservice.grpcserver.VerifyTokenServiceGrpc;

@Service
@Slf4j
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
