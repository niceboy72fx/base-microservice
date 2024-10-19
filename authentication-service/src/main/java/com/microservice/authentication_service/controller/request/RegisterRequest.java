package com.microservice.authentication_service.controller.request;

import com.microservice.authentication_service.common.Platform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @NotBlank(message = "username must be not null")
    private String username;

    @NotBlank(message = "username must be not blank")
    private String password;

    @NotNull(message = "username must be not null")
    private Platform platform;

    private String deviceToken;

    private String version;
}
