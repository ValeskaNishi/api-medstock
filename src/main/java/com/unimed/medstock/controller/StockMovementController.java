package com.unimed.medstock.controller;

import com.unimed.medstock.dto.ProfitDTO;
import com.unimed.medstock.dto.StockByTypeDTO;
import com.unimed.medstock.dto.StockMovementRequestDTO;
import com.unimed.medstock.dto.StockMovementResponseDTO;
import com.unimed.medstock.enums.ProductType;
import com.unimed.medstock.service.StockMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gerenciamento-estoque")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StockMovementController {

  private final StockMovementService stockMovementService;

  @GetMapping
  public ResponseEntity<List<StockMovementResponseDTO>> findAll() {
    return ResponseEntity.ok(stockMovementService.findAll());
  }

  @PostMapping
  public ResponseEntity<StockMovementResponseDTO> register(
      @RequestBody @Valid StockMovementRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(stockMovementService.register(dto));
  }

  @GetMapping("/stock-by-type/{type}")
  public ResponseEntity<List<StockByTypeDTO>> findStockByType(@PathVariable ProductType type) {
    return ResponseEntity.ok(stockMovementService.findStockByType(type));
  }

  @GetMapping("/profit")
  public ResponseEntity<List<ProfitDTO>> findProfit() {
    return ResponseEntity.ok(stockMovementService.findProfitByProduct());
  }
}
