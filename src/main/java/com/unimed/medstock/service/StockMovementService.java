package com.unimed.medstock.service;

import com.unimed.medstock.dto.ProfitDTO;
import com.unimed.medstock.dto.StockByTypeDTO;
import com.unimed.medstock.dto.StockMovementRequestDTO;
import com.unimed.medstock.dto.StockMovementResponseDTO;
import com.unimed.medstock.entity.Product;
import com.unimed.medstock.entity.StockMovement;
import com.unimed.medstock.enums.MovementType;
import com.unimed.medstock.enums.ProductType;
import com.unimed.medstock.exception.InsufficientStockException;
import com.unimed.medstock.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementService {

  private final StockMovementRepository stockMovementRepository;
  private final ProductService productService;

  public List<StockMovementResponseDTO> findAll() {
    return stockMovementRepository.findAll()
        .stream()
        .map(StockMovementResponseDTO::fromEntity)
        .toList();
  }

  @Transactional
  public StockMovementResponseDTO register(StockMovementRequestDTO dto) {
    Product product = productService.getById(dto.productId());

    if (dto.movementType() == MovementType.OUTFLOW) {
      validateStock(product, dto.quantity());
      product.setStockQuantity(product.getStockQuantity() - dto.quantity());
    } else {
      product.setStockQuantity(product.getStockQuantity() + dto.quantity());
    }

    StockMovement movement = StockMovement.builder()
        .product(product)
        .movementType(dto.movementType())
        .salePrice(dto.salePrice())
        .quantity(dto.quantity())
        .build();

    return StockMovementResponseDTO.fromEntity(stockMovementRepository.save(movement));
  }

  public List<StockByTypeDTO> findStockByType(ProductType type) {
    return stockMovementRepository.findStockByType(type);
  }

  public List<ProfitDTO> findProfitByProduct() {
    return stockMovementRepository.findProfitByProduct();
  }

  private void validateStock(Product product, Integer requested) {
    if (product.getStockQuantity() < requested) {
      throw new InsufficientStockException(
          product.getDescription(),
          product.getStockQuantity(),
          requested);
    }
  }
}
