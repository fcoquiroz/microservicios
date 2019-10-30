package com.circulosiete.curso.microservices.clientes.api;

import com.circulosiete.curso.microservices.clientes.modelo.ClienteBean;
import com.circulosiete.curso.microservices.clientes.service.ClientesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@RestController
@RequestMapping("/v1/clientes")
public class ClientesController {
  private final ClientesService service;

  public ClientesController(ClientesService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public ClienteBean createCliente(@RequestBody ClienteBean bean) {
    log.trace("Creando cliente en TRACE");
    log.debug("Creando cliente en DEBUG");
    log.info("Creando cliente en INFO");
    log.warn("Creando cliente en WARN");
    log.error("Creando cliente en ERROR");

    return service.createCliente(bean).to();
  }

  @GetMapping("/{id}")
  public ClienteBean read(@PathVariable("id") String id) {
    return service.findById(id).to();
  }

  @GetMapping("/_creditLimit")
  public Map<String, BigDecimal> getCreditLimit() {
    return unmodifiableMap(Stream.of(new SimpleEntry<>("limiteCredito", service.getLimite()))
      .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue)));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable("id") String id) {
    service.delete(id);
  }

  @PostMapping("/{id}/pedidosPendiente")
  public ClienteBean addPedidoPendiente(@PathVariable("id") String id) {
    return service.addPedido(id).to();
  }

  @DeleteMapping("/{id}/pedidosPendiente")
  public ClienteBean removePedidoPendiente(@PathVariable("id") String id) {
    return service.removerPedido(id).to();
  }

  @PostMapping("/{id}/pedidos")
  @ResponseStatus(CREATED)
  public PedidoBean addPedido(@PathVariable("id") String id, @RequestBody PedidoBean pedidoBean) {
    return service.addPedido(id, pedidoBean);
  }
}
