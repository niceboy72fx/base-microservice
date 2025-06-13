package com.microservice.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.product_service.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
