package com.circulosiete.curso.microservices.pedidos.service;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditLimit {
  BigDecimal limiteCredito;
}
