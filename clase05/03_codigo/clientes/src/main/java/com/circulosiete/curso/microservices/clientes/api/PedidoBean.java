package com.circulosiete.curso.microservices.clientes.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Slf4j
@Builder
public class PedidoBean {
  /**
   * Necesario por usar @Builder
   */
  @Tolerate
  public PedidoBean() {
    log.debug("Creando PedidoBean");
  }

  private String id;
  private Long version;
  private ZonedDateTime createdAt;
  private ZonedDateTime lastModifiedAt;

  private String clienteId;
  private BigDecimal importe;
  private Boolean pagado;
}
