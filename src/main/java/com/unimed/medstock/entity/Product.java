package com.unimed.medstock.entity;

import com.unimed.medstock.enums.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "Description is required")
  private String description;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @NotNull(message = "Product type is required")
  private ProductType type;

  @Column(nullable = false, precision = 10, scale = 2)
  @NotNull(message = "Supplier price is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Supplier price must be greater than zero")
  private BigDecimal supplierPrice;

  @Column(nullable = false)
  @NotNull(message = "Stock quantity is required")
  @Min(value = 0, message = "Quantity cannot be negative")
  private Integer stockQuantity;
}