package com.microservice.product_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.microservice.product_service.repositories.ProductRepository;
import com.microservice.product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.microservice.product_service.controller.request.ProductCreationRequest;
import com.microservice.product_service.controller.request.ProductUpdateRequest;
import com.microservice.product_service.models.Product;
import com.microservice.product_service.models.ProductDocument;
import com.microservice.product_service.repositories.ProductSearchRepository;

@Service
@Slf4j(topic = "PRODUCT-SERVICE")
public class ProductServiceImpl implements ProductService {
    private final ProductSearchRepository productSearchRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductSearchRepository productSearchRepository, ProductRepository productRepository) {
        this.productSearchRepository = productSearchRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long addProduct(ProductCreationRequest request) {

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setUserId(request.getUserId());

        productRepository.save(product);

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

    @Override
    public List<ProductDocument> searchProducts(String name) {

        List<ProductDocument> productDocuments = new ArrayList<>();

        if (StringUtils.hasLength(name)) {
            productDocuments = productSearchRepository.findByNameContaining(name);
        } else {
            Iterable<ProductDocument> documents = productSearchRepository.findAll();
            for (ProductDocument productDocument : documents) {
                productDocuments.add(productDocument);
            }
        }

        return productDocuments;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(ProductUpdateRequest request) {

        Product product = getProductById(request.getId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setUserId(request.getUserId());

        productRepository.save(product);

        if (product.getId() != null) {
            ProductDocument productDocument = getProductDocumentById(request.getId());
            productDocument.setId(product.getId());
            productDocument.setName(request.getName());
            productDocument.setDescription(request.getDescription());
            productDocument.setPrice(request.getPrice());
            productDocument.setUserId(request.getUserId());

            productSearchRepository.save(productDocument);

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
        productSearchRepository.deleteById(productId);
    }

    /**
     * Get product by id
     * @param id
     * @return
     */
    private Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Product not found"));
    }

    /**
     * Get Product Document by id
     * @param id
     * @return
     */
    private ProductDocument getProductDocumentById(long id) {
        return productSearchRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Product document not found"));
    }
}

