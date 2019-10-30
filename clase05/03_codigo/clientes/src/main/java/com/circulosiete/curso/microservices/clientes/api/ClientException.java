package com.circulosiete.curso.microservices.clientes.api;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {
  private final int errorStatus;
  private final String payload;

  public ClientException(String message, int errorStatus, String payload) {
    super(message);
    this.errorStatus = errorStatus;
    this.payload = payload;
  }
}
