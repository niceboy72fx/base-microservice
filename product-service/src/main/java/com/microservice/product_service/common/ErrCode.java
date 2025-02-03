package com.microservice.product_service.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public enum ErrCode {

    @JsonProperty("success")
    SUCCESS("success"),

    @JsonProperty("failed")
    FAILED("failed"),

    @JsonProperty("invalid_token")
    INVALID_TOKEN("invalid_token"),

    @JsonProperty("account_not_found")
    ACCOUNT_NOT_FOUND("account_not_found"),

    @JsonProperty("invalid_email")
    INVALID_EMAIL("invalid_email"),

    @JsonProperty("invalid_phone")
    INVALID_PHONE("invalid_phone"),

    @JsonProperty("invalid_username")
    INVALID_USERNAME("invalid_username"),

    @JsonProperty("invalid_password")
    INVALID_PASSWORD("invalid_password"),

    @JsonProperty("username_already_exists")
    USERNAME_ALREADY_EXISTS("username_already_exists"),

    @JsonProperty("email_already_exists")
    EMAIL_ALREADY_EXISTS("email_already_exists"),

    @JsonProperty("phone_already_exists")
    PHONE_ALREADY_EXISTS("phone_already_exists"),

    @JsonProperty("invalid_type")
    INVALID_TYPE("invalid_type"),

    @JsonProperty("invalid_status")
    INVALID_STATUS("invalid_status"),

    @JsonProperty("invalid_gender")
    INVALID_GENDER("invalid_gender"),

    @JsonProperty("invalid_date_of_birth")
    INVALID_DATE_OF_BIRTH("invalid_date_of_birth");

    private final String errCode;

    ErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }
}

