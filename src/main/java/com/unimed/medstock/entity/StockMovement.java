package com.unimed.medstock.entity;

import com.unimed.medstock.enums.MovementType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  @NotNull(message = "Product is required")
  private Product product;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @NotNull(message = "Movement type is required")
  private MovementType movementType;

  @Column(precision = 10, scale = 2)
  private BigDecimal salePrice;

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(nullable = false)
  @NotNull(message = "Quantity is required")
  @Min(value = 1, message = "Quantity must be at least 1")
  private Integer quantity;

  @PrePersist
  public void prePersist() {
    if (this.date == null) {
      this.date = LocalDateTime.now();
    }
  }
}
