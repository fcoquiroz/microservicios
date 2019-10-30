package com.circulosiete.curso.microservices.clientes.service.remote;

import com.circulosiete.curso.microservices.clientes.service.pedidos.ClientePedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "clientes", configuration = ClientRemoteConfig.class)
public interface ClienteRemoteClient {
  @RequestMapping(method = POST, value = "/v1/clientes/{id}/pedidosPendiente")
  ClientePedido addPedido(@PathVariable("id") String clienteId);

  @RequestMapping(method = DELETE, value = "/v1/clientes/{id}/pedidosPendiente")
  ClientePedido removePedido(@PathVariable("id") String clienteId);
}
