package com.circulosiete.curso.microservices.clientes.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@Slf4j
@Entity
@ToString
@Table(name = "clientes")
public class Cliente {
  /**
   * Necesario por usar @Builder
   */
  @Tolerate
  public Cliente() {
    log.debug("Creando Cliente");
  }

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(updatable = false)
  private String id;

  @Version
  private Long version;

  @Column(name = "created_at")
  @CreatedDate
  @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
  private ZonedDateTime createdAt;

  @Column(name = "last_modified_at")
  @LastModifiedDate
  @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
  private ZonedDateTime lastModifiedAt;

  private String firstName;
  private String lastName;
  private String taxId;
  private String email;
  private BigDecimal creditThreshold;
  private Boolean verified;
  private Integer pedidosPendientes;

  public static Cliente from(ClienteBean source) {
    return Cliente.builder()
      .createdAt(source.getCreatedAt())
      .creditThreshold(source.getCreditThreshold())
      .email(source.getEmail())
      .firstName(source.getFirstName())
      .id(source.getId())
      .lastModifiedAt(source.getLastModifiedAt())
      .lastName(source.getLastName())
      .taxId(source.getTaxId())
      .verified(source.getVerified())
      .version(source.getVersion())
      .pedidosPendientes(source.getPedidosPendientes())
      .build();
  }

  public ClienteBean to() {
    return ClienteBean.builder()
      .creditThreshold(getCreditThreshold())
      .createdAt(getCreatedAt())
      .email(getEmail())
      .firstName(getFirstName())
      .id(getId())
      .lastModifiedAt(getLastModifiedAt())
      .lastName(getLastName())
      .taxId(getTaxId())
      .verified(getVerified())
      .version(getVersion())
      .pedidosPendientes(getPedidosPendientes())
      .build();
  }
}
