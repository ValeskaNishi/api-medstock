package com.unimed.medstock.dto;

import com.unimed.medstock.enums.ProductType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequestDTO(

    @NotBlank(message = "Description is required") String description,

    @NotNull(message = "Product type is required") ProductType type,

    @NotNull(message = "Supplier price is required") @DecimalMin(value = "0.0", inclusive = false, message = "Supplier price must be greater than zero") BigDecimal supplierPrice,

    @NotNull(message = "Stock quantity is required") @Min(value = 0, message = "Quantity cannot be negative") Integer stockQuantity) {
}