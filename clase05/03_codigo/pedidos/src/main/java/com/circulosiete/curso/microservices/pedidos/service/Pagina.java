package com.circulosiete.curso.microservices.pedidos.service;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class Pagina<T> {
  private int number;
  private int size;
  private int numberOfElements;
  private List<T> content;
  private boolean hasContent;
}
