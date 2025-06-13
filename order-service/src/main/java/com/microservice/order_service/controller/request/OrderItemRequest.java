package com.microservice.order_service.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderItemRequest implements Serializable {
    private Long productId;
    private Integer quantity;
    private Double price;
    private String unit;
}
