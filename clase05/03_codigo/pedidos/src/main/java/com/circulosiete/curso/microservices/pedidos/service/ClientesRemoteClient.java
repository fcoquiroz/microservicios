package com.circulosiete.curso.microservices.pedidos.service;

import com.circulosiete.curso.microservices.pedidos.config.ClientRemoteConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "clientes", configuration = ClientRemoteConfig.class)
public interface ClientesRemoteClient {
  @RequestMapping(method = RequestMethod.GET, value = "/v1/clientes/_creditLimit")
  CreditLimit getCreditLimit();
}
