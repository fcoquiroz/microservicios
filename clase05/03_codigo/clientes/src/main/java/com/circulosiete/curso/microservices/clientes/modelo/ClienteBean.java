package com.circulosiete.curso.microservices.clientes.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Slf4j
@Builder
@ToString
public class ClienteBean {
  /**
   * Necesario por usar @Builder
   */
  @Tolerate
  public ClienteBean() {
    log.debug("Creando Cliente");
  }

  private String id;
  private Long version;
  private ZonedDateTime createdAt;
  private ZonedDateTime lastModifiedAt;

  private String firstName;
  private String lastName;
  private String taxId;
  private String email;
  private BigDecimal creditThreshold;
  private Boolean verified;
  private Integer pedidosPendientes;
  //private String observaciones;
}
