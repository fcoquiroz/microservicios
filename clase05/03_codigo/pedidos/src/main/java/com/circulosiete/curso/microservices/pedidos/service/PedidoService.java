package com.circulosiete.curso.microservices.pedidos.service;

import com.circulosiete.curso.microservices.pedidos.api.CreditoNoDisponibleException;
import com.circulosiete.curso.microservices.pedidos.api.PedidoBean;
import com.circulosiete.curso.microservices.pedidos.modelo.Pedido;
import com.circulosiete.curso.microservices.pedidos.modelo.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class PedidoService {
  private final PedidoRepository repository;
  private final ClientesRemoteClient remoteClient;

  public PedidoService(PedidoRepository repository, ClientesRemoteClient remoteClient) {
    this.repository = repository;
    this.remoteClient = remoteClient;
  }

  public BigDecimal totalAdeudo(String clientId) {
    PageRequest pageRequest = PageRequest.of(0, 100);
    Page<Pedido> paginaPedidosPendientesDePago = repository.findAllByClienteIdAndPagado(clientId, false, pageRequest);

    List<Pedido> pedidosPendientesDePago = paginaPedidosPendientesDePago.getContent();

    return pedidosPendientesDePago.stream()
      .map(Pedido::getImporte)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

  }

  public Pedido crear(PedidoBean bean) {
    CreditLimit creditLimit = remoteClient.getCreditLimit();

    BigDecimal totalPendienteDePago = totalAdeudo(bean.getClienteId());

    int compareTo = bean.getImporte().add(totalPendienteDePago).compareTo(creditLimit.limiteCredito);

    if (compareTo > 0) {
      throw new CreditoNoDisponibleException("No hay crédito disponible", creditLimit.limiteCredito, totalPendienteDePago, bean.getImporte());
    }

    return repository.save(Pedido.from(bean));
  }

  public void delete(String pedidoId) {
    repository.deleteById(pedidoId);
  }

  public Page<Pedido> ordersByClientId(String clientId, Pageable page) {
    return repository.findAllByClienteId(clientId, page);
  }
}
