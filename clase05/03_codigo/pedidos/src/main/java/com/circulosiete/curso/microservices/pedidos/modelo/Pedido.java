package com.circulosiete.curso.microservices.pedidos.modelo;

import com.circulosiete.curso.microservices.pedidos.api.PedidoBean;
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
@Table(name = "pedidos")
public class Pedido {
  @Tolerate
  public Pedido(){}
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

  private String clienteId;
  private BigDecimal importe;
  private Boolean pagado;

  public static Pedido from(PedidoBean source) {
    return Pedido.builder()
      .createdAt(source.getCreatedAt())
      .id(source.getId())
      .lastModifiedAt(source.getLastModifiedAt())
      .version(source.getVersion())
      .clienteId(source.getClienteId())
      .importe(source.getImporte())
      .pagado(source.getPagado())
      .build();
  }

  public PedidoBean to() {
    return PedidoBean.builder()
      .createdAt(getCreatedAt())
      .id(getId())
      .lastModifiedAt(getLastModifiedAt())
      .version(getVersion())
      .clienteId(getClienteId())
      .importe(getImporte())
      .pagado(getPagado())
      .build();
  }

}
