package com.iconique.product_servce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iconique.product_servce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
