package com.microservice.product_service.services;

import java.util.List;

import com.microservice.product_service.controller.request.ProductCreationRequest;
import com.microservice.product_service.controller.request.ProductUpdateRequest;
import com.microservice.product_service.models.ProductDocument;

public interface ProductService {
    long addProduct(ProductCreationRequest request);

    List<ProductDocument> searchProducts(String name);

    void updateUser(ProductUpdateRequest product);

    void deleteProduct(long productId);
}
