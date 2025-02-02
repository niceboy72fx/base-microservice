package com.microservice.account_service.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum UserStatus {

    @JsonProperty("active")
    ACTIVE("active"),

    @JsonProperty("inactive")
    INACTIVE("inactive"),

    @JsonProperty("locked")
    LOCKED("locked"),

    @JsonProperty("none")
    NONE("none");

    private final String userStatus;

    UserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
