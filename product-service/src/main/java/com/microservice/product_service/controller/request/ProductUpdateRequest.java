package com.microservice.product_service.controller.request;

public class ProductUpdateRequest {
    private long id;
    private String name;
    private String description;
    private double price;
    private int userId;

    public ProductUpdateRequest(String description, long id, String name, double price, int userId) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.price = price;
        this.userId = userId;
    }

    public ProductUpdateRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
