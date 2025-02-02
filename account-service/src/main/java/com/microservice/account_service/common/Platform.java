package com.microservice.account_service.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Platform {
    @JsonProperty("web")
    WEB("web"),

    @JsonProperty("ios")
    IOS("ios"),

    @JsonProperty("android")
    ANDROID("android"),

    @JsonProperty("miniApp")
    MINI_APP("miniApp");

    private final String platformName;

    Platform(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformName() {
        return platformName;
    }
}
