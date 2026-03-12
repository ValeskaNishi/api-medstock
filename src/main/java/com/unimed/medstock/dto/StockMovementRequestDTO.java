package com.unimed.medstock.dto;

import com.unimed.medstock.enums.MovementType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record StockMovementRequestDTO(

        @NotNull(message = "Product ID is required") Long productId,

        @NotNull(message = "Movement type is required") MovementType movementType,

        BigDecimal salePrice,

        @NotNull(message = "Quantity is required") @Min(value = 1, message = "Quantity must be at least 1") Integer quantity) {
}
