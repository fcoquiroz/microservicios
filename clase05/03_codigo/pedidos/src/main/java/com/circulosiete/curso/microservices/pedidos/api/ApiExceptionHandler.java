package com.circulosiete.curso.microservices.pedidos.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(CreditoNoDisponibleException.class)
  public HttpEntity ex(CreditoNoDisponibleException exception) {
    HashMap<String, Object> body = new HashMap<>();
    body.put("creditLimit", exception.getCreditLimit());
    body.put("message", exception.getMessage());
    body.put("debt", exception.getDebt());
    body.put("importeNuevoPedido", exception.getImporteNuevoPedido());

    return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(body);
  }

}
