package com.unimed.medstock.dto;

import com.unimed.medstock.entity.Product;
import com.unimed.medstock.enums.ProductType;
import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String description,
        ProductType type,
        BigDecimal supplierPrice,
        Integer stockQuantity) {
    public static ProductResponseDTO fromEntity(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getDescription(),
                product.getType(),
                product.getSupplierPrice(),
                product.getStockQuantity());
    }
}