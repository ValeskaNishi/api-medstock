package com.unimed.medstock.controller;

import com.unimed.medstock.dto.ProductRequestDTO;
import com.unimed.medstock.dto.ProductResponseDTO;
import com.unimed.medstock.enums.ProductType;
import com.unimed.medstock.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductResponseDTO>> findAll() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.findById(id));
  }

  @GetMapping("/categoria/{type}")
  public ResponseEntity<List<ProductResponseDTO>> findByType(@PathVariable ProductType type) {
    return ResponseEntity.ok(productService.findByType(type));
  }

  @PostMapping
  public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> update(
      @PathVariable Long id,
      @RequestBody @Valid ProductRequestDTO dto) {
    return ResponseEntity.ok(productService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
