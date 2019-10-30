package com.circulosiete.curso.microservices.clientes.service.pedidos;

import com.circulosiete.curso.microservices.clientes.api.PedidoBean;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class ClientePedido {
  @Tolerate
  public ClientePedido() {
  }

  private String personId;
  private BigDecimal deuda;
  private PedidoBean pedido;
}
