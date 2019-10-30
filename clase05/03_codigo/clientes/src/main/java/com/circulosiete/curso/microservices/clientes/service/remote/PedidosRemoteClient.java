package com.circulosiete.curso.microservices.clientes.service.remote;

import com.circulosiete.curso.microservices.clientes.api.PedidoBean;
import com.circulosiete.curso.microservices.clientes.service.Pagina;
import com.circulosiete.curso.microservices.clientes.service.pedidos.ClientePedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "pedidos", configuration = ClientRemoteConfig.class)
public interface PedidosRemoteClient {
  @RequestMapping(method = POST, value = "/v1/pedidos")
  ClientePedido addPedido(PedidoBean pedido);

  @RequestMapping(method = GET, value = "/v1/pedidos/client/{id}")
  Pagina<PedidoBean> pedidos(@PathVariable("id") String id);
}

