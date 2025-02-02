package com.microservice.account_service.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Gender {

    @JsonProperty("male")
    MALE("male"),

    @JsonProperty("female")
    FEMALE("female"),

    @JsonProperty("other")
    OTHER("other");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @JsonCreator
    public static Gender fromValue(int value) {
        switch (value) {
            case 1:
                return MALE;
            case 2:
                return FEMALE;
            case 3:
                return OTHER;
            default:
                throw new IllegalArgumentException("Invalid gender value: " + value);
        }
    }
}
