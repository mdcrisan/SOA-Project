package com.iconique.user_servce.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRequest {

	private Long id;
    private String productName;
    private String productDescription;
    private BigDecimal unitPrice;
    private Integer availableCount;
}
