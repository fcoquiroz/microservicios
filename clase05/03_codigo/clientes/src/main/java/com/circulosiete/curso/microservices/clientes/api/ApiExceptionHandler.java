package com.circulosiete.curso.microservices.clientes.api;

import com.circulosiete.curso.microservices.clientes.service.ClientHasOrdersException;
import com.circulosiete.curso.microservices.clientes.service.ClienteNoEncontradoException;
import com.circulosiete.curso.microservices.clientes.service.CreditLimitException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;

@RestControllerAdvice
public class ApiExceptionHandler {
  @Autowired
  private ObjectMapper objectMapper;

  //
  @ExceptionHandler(ClientHasOrdersException.class)
  public HttpEntity hasOrders(ClientHasOrdersException exception) {
    HashMap<String, Object> body = new HashMap<>();
    body.put("clientId", exception.getClientId());

    return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(body);
  }

  @ExceptionHandler(CreditLimitException.class)
  public HttpEntity ex(CreditLimitException exception) {
    HashMap<String, Object> body = new HashMap<>();
    body.put("creditThreshold", exception.getCreditThreshold());
    body.put("message", exception.getMessage());
    body.put("requestedCredit", exception.getInvalidCreditLimitAmount());

    return ResponseEntity.unprocessableEntity().body(body);
  }

  @ExceptionHandler(ClienteNoEncontradoException.class)
  public HttpEntity notFound(ClienteNoEncontradoException exception) {
    HashMap<String, Object> body = new HashMap<>();
    body.put("id", exception.getId());
    body.put("message", exception.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }


  @ExceptionHandler(ClientException.class)
  public HttpEntity genricClient(ClientException exception) {
    TypeReference<HashMap<String, Object>> typeRef
      = new TypeReference<HashMap<String, Object>>() {
    };

    HashMap o = null;
    try {
      o = objectMapper.readValue(exception.getPayload(), typeRef);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.valueOf(exception.getErrorStatus())).body(o);
  }
}
