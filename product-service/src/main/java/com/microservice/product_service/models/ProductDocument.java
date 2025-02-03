package com.microservice.product_service.models;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(indexName = "products")
public class ProductDocument {
    @Id
    private long id;
    private String name;
    private String description;
    private double price;
    private int userId;
}
