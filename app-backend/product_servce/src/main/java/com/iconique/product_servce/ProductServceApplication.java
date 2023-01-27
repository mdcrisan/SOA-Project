package com.iconique.product_servce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class ProductServceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServceApplication.class, args);
	}

}
