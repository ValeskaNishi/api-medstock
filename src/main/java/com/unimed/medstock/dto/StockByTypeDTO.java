package com.unimed.medstock.dto;

import com.unimed.medstock.enums.ProductType;

public record StockByTypeDTO(
    String description,
    ProductType type,
    Integer availableQuantity,
    Long totalOutflows) {
}
