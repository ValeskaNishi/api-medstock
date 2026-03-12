package com.unimed.medstock.service;

import com.unimed.medstock.dto.ProductRequestDTO;
import com.unimed.medstock.dto.ProductResponseDTO;
import com.unimed.medstock.entity.Product;
import com.unimed.medstock.enums.ProductType;
import com.unimed.medstock.exception.ProductHasMovementsException;
import com.unimed.medstock.exception.ResourceNotFoundException;
import com.unimed.medstock.repository.ProductRepository;
import com.unimed.medstock.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final StockMovementRepository stockMovementRepository;

  public List<ProductResponseDTO> findAll() {
    return productRepository.findAll()
        .stream()
        .map(ProductResponseDTO::fromEntity)
        .toList();
  }

  public ProductResponseDTO findById(Long id) {
    return ProductResponseDTO.fromEntity(getById(id));
  }

  public List<ProductResponseDTO> findByType(ProductType type) {
    return productRepository.findByType(type)
        .stream()
        .map(ProductResponseDTO::fromEntity)
        .toList();
  }

  @Transactional
  public ProductResponseDTO create(ProductRequestDTO dto) {
    Product product = Product.builder()
        .description(dto.description())
        .type(dto.type())
        .supplierPrice(dto.supplierPrice())
        .stockQuantity(dto.stockQuantity())
        .build();

    return ProductResponseDTO.fromEntity(productRepository.save(product));
  }

  @Transactional
  public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
    Product product = getById(id);

    product.setDescription(dto.description());
    product.setType(dto.type());
    product.setSupplierPrice(dto.supplierPrice());
    product.setStockQuantity(dto.stockQuantity());

    return ProductResponseDTO.fromEntity(productRepository.save(product));
  }

  @Transactional
  public void delete(Long id) {
    Product product = getById(id);

    if (stockMovementRepository.existsByProductId(id)) {
      throw new ProductHasMovementsException(product.getDescription());
    }

    productRepository.delete(product);
  }

  public Product getById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product", id));
  }
}