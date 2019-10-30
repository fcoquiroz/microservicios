package com.circulosiete.curso.microservices.web;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discovery")
public class ServicioController {
  private final DiscoveryClient discoveryClient;

  public ServicioController(DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }

  @GetMapping
  public List<ServiceInstance> data() throws Exception {
    List<ServiceInstance> data = discoveryClient.getInstances("miservicio");

    return data;
  }

  @GetMapping("/foo")
  public Map foo() {
    System.out.println("Invocando FOO: " + System.currentTimeMillis());

    Map<String, Object> result = new HashMap<>();
    result.put("hola", "Extraño");

    return result;
  }
}
