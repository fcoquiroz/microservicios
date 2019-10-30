package com.circulosiete.curso.microservices.pedidos.modelo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Para la persistencia de Pedidos.
 * <p>
 * Generalmente la anotación @Transactional va en los @Service, ya que los @Service son los encargados de
 * definir el contexto transaccional, ellos inician las transacciones. Pero a veces es conveniente hacer explicito
 * el manejo de transacciones en el nivel @Repository.
 */
@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, String> {
  Page<Pedido> findAllByClienteId(String clienteId, Pageable page);
  Page<Pedido> findAllByClienteIdAndPagado(String clienteId, Boolean pagado, Pageable page);
}
