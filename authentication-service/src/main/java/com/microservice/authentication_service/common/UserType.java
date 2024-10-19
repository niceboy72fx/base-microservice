package com.microservice.authentication_service.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum UserType {
    @JsonProperty("owner")
    OWNER("owner"),

    @JsonProperty("admin")
    ADMIN("admin"),

    @JsonProperty("user")
    USER("user");

    private String value;

    private final String userType;

    UserType(String userType){
        this.userType = userType;
    }
}
