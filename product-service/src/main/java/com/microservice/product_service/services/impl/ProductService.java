package com.microservice.product_service.services.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.microservice.product_service.controller.request.ProductCreationRequest;
import com.microservice.product_service.controller.request.ProductUpdateRequest;
import com.microservice.product_service.models.Product;
import com.microservice.product_service.models.ProductDocument;
import com.microservice.product_service.repositories.ProductSearchRepository;

public class ProductService {
    private final ProductSearchRepository productSearchRepository;
    private final JpaRepository<Product, Long> productRepository;

    public ProductService(ProductSearchRepository productSearchRepository,
                           JpaRepository<Product, Long> productRepository) {
        this.productSearchRepository = productSearchRepository;
        this.productRepository = productRepository;
    }
    
    @Transactional(rollbackFor = Exception.class)
    public long addProduct(ProductCreationRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setUserId(request.getUserId());

        product = productRepository.save(product);

        if (product.getId() != null) {
            ProductDocument productDocument = new ProductDocument();
            productDocument.setId(product.getId());
            productDocument.setName(request.getName());
            productDocument.setDescription(request.getDescription());
            productDocument.setPrice(request.getPrice());
            productDocument.setUserId(request.getUserId());

            productSearchRepository.save(productDocument);
        }

        return product.getId();
    }

    public List<ProductDocument> searchProducts(String name) {
        List<ProductDocument> productDocuments;

        if (StringUtils.hasLength(name)) {
            productDocuments = productSearchRepository.findByNameContaining(name);
        } else {
            productDocuments = (List<ProductDocument>) productSearchRepository.findAll();
        }

        return productDocuments;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(ProductUpdateRequest request) {
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setUserId(request.getUserId());
        product = productRepository.save(product);

        if (product.getId() != null) {
            ProductDocument productDocument = productSearchRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("Product document not found"));
            productDocument.setId(product.getId());
            productDocument.setName(request.getName());
            productDocument.setDescription(request.getDescription());
            productDocument.setPrice(request.getPrice());
            productDocument.setUserId(request.getUserId());
            productSearchRepository.save(productDocument);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
        productSearchRepository.deleteById(productId);
    }
}

