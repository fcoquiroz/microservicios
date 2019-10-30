package com.circulosiete.curso.microservices.pedidos.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientRemoteConfig {
  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${clientes.username:admin}") String username,
                                                                 @Value("${clientes.password:secreto}") String password) {
    return new BasicAuthRequestInterceptor(username, password);
  }
}
