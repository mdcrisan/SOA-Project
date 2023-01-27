package com.iconique.product_servce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.iconique.product_servce.model.Product;
import com.iconique.product_servce.repository.ProductRepository;
import com.iconique.user_servce.dto.ProductRequest;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * This method is used to create a new product by taking new product details from controller API
	 * After successful product creation, the API send a response with newly created product details
	 */
	@Override
	public Product createProduct(ProductRequest userRequest) {
		return productRepository.save(
				Product.builder()
				.productName(userRequest.getProductName())
				.productDescription(userRequest.getProductDescription())
				.unitPrice(userRequest.getUnitPrice())
				.availableCount(userRequest.getAvailableCount())
				.build());
	}

	/**
	 * This method is used to get product item details DB table by parsing the product ID as coming from controller API
	 * The fetched product object returns to the controller API as response, otherwise throws a not found error
	 */
	@Override
	public Product getProductById(long parseLong) {
		return productRepository.findById(parseLong).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product" +parseLong+ "not found"));
	}

	/**
	 * This method is used to update existing product record using newly changed details which are coming from controller
	 * Updated product detail object is returned as response in successful updating, otherwise throws a not found exception.
	 */
	@Override
	public Product updateProduct(ProductRequest userRequest) {
		Optional<Product> productOpt = productRepository.findById(userRequest.getId());
		if (!productOpt.isEmpty()) {
			Product product = productOpt.get();
			product.setProductName(userRequest.getProductName());
			product.setProductDescription(userRequest.getProductDescription());
			product.setUnitPrice(userRequest.getUnitPrice());
			product.setAvailableCount(userRequest.getAvailableCount());
			return productRepository.save(product);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product" +userRequest.getId()+ "not found");
		}
		
	}

	/**
	 * This method is used to delete existing product record using product ID which is coming from controller
	 * nothing is returned as response in successful deletion, otherwise throws a not found exception.
	 */
	@Override
	public void deleteProduct(long parseLong) {
		Optional<Product> productOpt = productRepository.findById(parseLong);
		if (!productOpt.isEmpty()) {
			Product product = productOpt.get();
			productRepository.delete(product);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product" +parseLong+ "not found");
		}
		
	}

	/**
	 * This method is used to fetch all the products from DB table
	 * All the DB records are returned to the controller as response
	 */
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}
