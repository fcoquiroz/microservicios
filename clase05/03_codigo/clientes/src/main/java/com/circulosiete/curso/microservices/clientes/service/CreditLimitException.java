package com.circulosiete.curso.microservices.clientes.service;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreditLimitException extends RuntimeException {
  private final BigDecimal creditThreshold;
  private final BigDecimal invalidCreditLimitAmount;

  public CreditLimitException(String message, BigDecimal creditThreshold, BigDecimal invalidCreditLimitAmount) {
    super(message);
    this.creditThreshold = creditThreshold;
    this.invalidCreditLimitAmount = invalidCreditLimitAmount;
  }

  public static CreditLimitException from(String message, BigDecimal creditThreshold, BigDecimal invalidCreditLimitAmount) {
    return new CreditLimitException(message, creditThreshold, invalidCreditLimitAmount);
  }
}
