package com.unimed.medstock.dto;

import com.unimed.medstock.enums.ProductType;
import java.math.BigDecimal;

public record ProfitDTO(
                String description,
                ProductType type,
                Long totalOutflows,
                BigDecimal totalProfit) {
}