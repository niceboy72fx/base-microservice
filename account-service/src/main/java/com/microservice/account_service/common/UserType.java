package com.microservice.account_service.common;

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

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

}
