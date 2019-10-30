package com.circulosiete.curso.microservices.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FetcherService {
  private final RestTemplate restTemplate;

  public FetcherService() {
    this.restTemplate = new RestTemplate();
  }

  public ResponseEntity<String> fetch(String url) {
    //Programación defensiva aqui...
    return restTemplate
      .getForEntity(url, String.class);
  }

  //Mira mamá! sin instanciar comandos, pero usando AOP!
  @HystrixCommand(fallbackMethod = "fetchJavanicaFallback")
  public String fetchJavanica(String url) {
    return restTemplate
      .getForEntity(url, String.class).getBody();
  }

  public String fetchJavanicaFallback(String url) {
    return "la url: " + url + ", no se pudo obtener";
  }
}
