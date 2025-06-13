package com.microservice.order_service.common;

import lombok.Getter;

@Getter
public enum OrderStatus {

    NEW(1),
    PENDING(2),
    ACCEPTED(3),
    REJECTED(4),
    PROCESSING(5),
    CANCELED(6),
    PAID(7),
    CLOSED(0);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }
}
