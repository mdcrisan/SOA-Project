package com.iconique.product_servce.service;

import java.util.List;

import com.iconique.product_servce.model.Product;
import com.iconique.user_servce.dto.ProductRequest;

public interface ProductService {

	Product createProduct(ProductRequest userRequest);

	Product getProductById(long parseLong);

	Product updateProduct(ProductRequest userRequest);

	void deleteProduct(long parseLong);

	List<Product> getAllProducts();

}
