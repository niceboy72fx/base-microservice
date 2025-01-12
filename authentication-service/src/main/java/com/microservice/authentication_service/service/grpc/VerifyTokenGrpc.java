package com.microservice.authentication_service.service.grpc;

import com.microservice.authentication_service.common.TokenType;
import com.microservice.authentication_service.service.implement.JwtServiceImplement;
import com.microservice.grpcserver.VerifyRequest;
import com.microservice.grpcserver.VerifyResponse;
import com.microservice.grpcserver.VerifyTokenServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.security.SignatureException;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class VerifyTokenGrpc extends VerifyTokenServiceGrpc.VerifyTokenServiceImplBase {

        private final JwtServiceImplement jwtService;

        @Override
        public void verifyAccessToken(VerifyRequest request, StreamObserver<VerifyResponse> responseObserver) {
            log.info("-----[ verifyToken ]-----");
            VerifyResponse response;
            try {
                jwtService.extractUsername(request.getToken(), TokenType.ACCESS_TOKEN);
                response = VerifyResponse.newBuilder().setIsVerified(true).setMessage("Token is valid").build();
            } catch (IllegalArgumentException e) {
                response = VerifyResponse.newBuilder().setIsVerified(false).setMessage(e.getMessage()).build();
            } catch (ExpiredJwtException ex) {
                response = VerifyResponse.newBuilder().setIsVerified(false).setMessage(ex.getMessage()).build();
            }

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

}
