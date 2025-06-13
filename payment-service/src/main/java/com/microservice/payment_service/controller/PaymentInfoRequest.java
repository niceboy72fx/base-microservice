package com.microservice.payment_service.controller;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class PaymentInfoRequest implements Serializable {
    private long amount;
    private String currency;
    private String receiptEmail;
}
