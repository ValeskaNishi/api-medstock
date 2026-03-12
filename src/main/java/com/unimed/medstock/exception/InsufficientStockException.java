package com.unimed.medstock.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String description, Integer available, Integer requested) {
        super(String.format(
                "Estoque insuficiente para o produto '%s'. Disponível: %d unidade(s). Solicitado: %d unidade(s).",
                description, available, requested));
    }
}