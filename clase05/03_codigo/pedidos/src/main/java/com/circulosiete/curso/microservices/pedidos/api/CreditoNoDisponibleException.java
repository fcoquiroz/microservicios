package com.circulosiete.curso.microservices.pedidos.api;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreditoNoDisponibleException extends RuntimeException {
  private final BigDecimal creditLimit;
  private final BigDecimal debt;
  private final BigDecimal importeNuevoPedido;

  public CreditoNoDisponibleException(String message, BigDecimal creditLimit, BigDecimal debt, BigDecimal importeNuevoPedido) {
    super(message);
    this.creditLimit = creditLimit;
    this.debt = debt;
    this.importeNuevoPedido = importeNuevoPedido;
  }
}
