package com.unimed.medstock.exception;

public class ProductHasMovementsException extends RuntimeException {

  public ProductHasMovementsException(String description) {
    super("O produto '" + description + "' possui movimentações registradas e não pode ser excluído.");
  }
}