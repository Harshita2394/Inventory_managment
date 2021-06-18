package com.project.inventorymanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.inventorymanagement.domain.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>{

}
