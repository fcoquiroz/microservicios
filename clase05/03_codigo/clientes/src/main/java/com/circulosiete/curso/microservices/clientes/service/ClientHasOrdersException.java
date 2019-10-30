package com.circulosiete.curso.microservices.clientes.service;

import lombok.Getter;

@Getter
public class ClientHasOrdersException extends RuntimeException {
  private final String clientId;

  public ClientHasOrdersException(String clientId, String message) {
    super(message);
    this.clientId = clientId;
  }
}
