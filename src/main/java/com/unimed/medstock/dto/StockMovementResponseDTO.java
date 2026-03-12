package com.unimed.medstock.dto;

import com.unimed.medstock.entity.StockMovement;
import com.unimed.medstock.enums.MovementType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockMovementResponseDTO(
        Long id,
        Long productId,
        String productDescription,
        MovementType movementType,
        BigDecimal salePrice,
        LocalDateTime date,
        Integer quantity) {
    public static StockMovementResponseDTO fromEntity(StockMovement m) {
        return new StockMovementResponseDTO(
                m.getId(),
                m.getProduct().getId(),
                m.getProduct().getDescription(),
                m.getMovementType(),
                m.getSalePrice(),
                m.getDate(),
                m.getQuantity());
    }
}
