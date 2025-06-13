package com.microservice.product_service.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.microservice.product_service.controller.request.ProductCreationRequest;
import com.microservice.product_service.controller.request.ProductUpdateRequest;
import com.microservice.product_service.models.ProductDocument;
import com.microservice.product_service.services.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list-product")
    public List<ProductDocument> getProductList(@RequestParam(required = false) String name) {
        return productService.searchProducts(name);
    }

    @PostMapping("/add-product")
    public long addProduct(@RequestBody ProductCreationRequest request) {
        return productService.addProduct(request);
    }

    @PutMapping("/update-product")
    public void updateProduct(@RequestBody ProductUpdateRequest request) {
        productService.updateUser(request);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }
}
