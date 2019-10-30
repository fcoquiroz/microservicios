package com.circulosiete.curso.microservices.clientes.service;

import com.circulosiete.curso.microservices.clientes.api.PedidoBean;
import com.circulosiete.curso.microservices.clientes.modelo.Cliente;
import com.circulosiete.curso.microservices.clientes.modelo.ClienteBean;
import com.circulosiete.curso.microservices.clientes.modelo.ClientesRepository;
import com.circulosiete.curso.microservices.clientes.service.remote.ClienteRemoteClient;
import com.circulosiete.curso.microservices.clientes.service.remote.PedidosRemoteClient;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@Transactional
public class ClientesService {
  private final PedidosRemoteClient pedidosRemoteClient;
  private final ClienteRemoteClient clienteRemoteClient;
  private final ClientesRepository repository;

  private final MeterRegistry meterRegistry;
  private final Boolean validatePedidos;
  private final Counter customerCounter;

  @Getter
  private final BigDecimal limite;


  public ClientesService(PedidosRemoteClient pedidosRemoteClient, ClienteRemoteClient clienteRemoteClient,
                         ClientesRepository repository, MeterRegistry meterRegistry,
                         @Value("${clientes.limiteCredito.max:100000.0}") BigDecimal limite,
                         @Value("${pedidos.validate:true}") Boolean validatePedidos) {
    this.pedidosRemoteClient = pedidosRemoteClient;
    this.clienteRemoteClient = clienteRemoteClient;
    this.repository = repository;
    this.meterRegistry = meterRegistry;
    this.validatePedidos = validatePedidos;
    customerCounter = this.meterRegistry.counter("new.customer");
    this.limite = limite;
  }

  public Cliente createCliente(ClienteBean bean) {
    Timer.Sample sample = null;
    try {
      sample = Timer.start(meterRegistry);
    } catch (Throwable t) {

    }

    Cliente cliente = Cliente.from(bean);
    if (!isCreditLimitValid(bean.getCreditThreshold())) {
      throw CreditLimitException.from(String.format("El límite de crédito se excedió. El máximo permitido es de %s, el solicitado fue de %s", limite, bean.getCreditThreshold()), limite, bean.getCreditThreshold());
    }

    Cliente clientSaved = repository.save(cliente);
    customerCounter.increment();
    if (!Objects.isNull(sample)) {
      sample.stop(meterRegistry.timer("add.customer", "response", "OK"));
    }

    return clientSaved;
  }

  public boolean isCreditLimitValid(BigDecimal other) {
    return other.compareTo(limite) <= 0;
  }

  public Cliente findById(String id) {
    return repository.findById(id)
      .orElseThrow(() -> ClienteNoEncontradoException.from("No se encontro al cliente con el id: '%s'", id));
  }

  public void delete(String id) {
    if (repository.findById(id).isPresent()) {
      if(validatePedidos) {
        Pagina<PedidoBean> pedidos = this.pedidosRemoteClient.pedidos(id);
        if (!pedidos.getContent().isEmpty()) {
          repository.deleteById(id);
        } else {
          throw new ClientHasOrdersException(id, "El cliente tiene " + pedidos.getNumberOfElements() + " pedidos. No es posible eliminarlo");
        }
      }
      repository.deleteById(id);
    } else {
      throw ClienteNoEncontradoException.from("No se encontro al cliente con el id: '%s'", id);
    }
  }

  public Cliente addPedido(String clienteId) {
    return alterarPedidos(clienteId, 1);
  }

  public Cliente removerPedido(String clienteId) {
    return alterarPedidos(clienteId, -1);
  }

  public Cliente alterarPedidos(String clienteId, Integer numPedidos) {
    Cliente cliente = findById(clienteId);
    cliente.setPedidosPendientes(cliente.getPedidosPendientes() + numPedidos);
    return repository.save(cliente);
  }

  public PedidoBean addPedido(String clienteId, PedidoBean pedido) {
    pedido.setClienteId(clienteId);
    pedido.setPagado(false);
    clienteRemoteClient.addPedido(clienteId);
    PedidoBean pedidoBean;

    try {
      pedidoBean = pedidosRemoteClient.addPedido(pedido).getPedido();
    } catch (Throwable t) {
      clienteRemoteClient.removePedido(clienteId);
      throw t;
    }
    return pedidoBean;
  }

}
