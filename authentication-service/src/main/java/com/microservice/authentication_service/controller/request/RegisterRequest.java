package com.microservice.authentication_service.controller.request;

import com.microservice.authentication_service.common.Gender;
import com.microservice.authentication_service.common.Platform;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public class RegisterRequest {
    @NotBlank(message = "username must be not null")
    private String username;

    @NotBlank(message = "username must be not blank")
    private String password;

    @NotNull(message = "gender must be not null")
    private Gender gender;

    @NotNull(message = "Phone's number must be not blank !")
    private String phone;

    @NotNull(message = "Email must be not blank")
    private String email;

    @NotNull(message = "first_name must be not blank")
    private String firstName;

    @NotNull(message = "last_name must be not blank")
    private String lastName;

    @NotNull(message = "platform must be not null")
    private Platform platform;

    private String deviceToken;

    private String version;

    @NotNull(message = "dateOfBirth must be not null")
    private Date dateOfBirth;
}
