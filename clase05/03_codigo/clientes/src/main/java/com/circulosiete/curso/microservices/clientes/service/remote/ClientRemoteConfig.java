package com.circulosiete.curso.microservices.clientes.service.remote;

import com.circulosiete.curso.microservices.clientes.api.ClientException;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

import static feign.FeignException.errorStatus;

@Configuration
public class ClientRemoteConfig {
  @Bean
  public ErrorDecoder errorDecoder() {
    return (methodKey, response) -> {
      InputStream initialStream;
      byte[] targetArray;
      String result = "";
      try {
        initialStream = response.body().asInputStream();
        targetArray = new byte[initialStream.available()];
        initialStream.read(targetArray);
        result = new String(targetArray);
      } catch (IOException e) {
        e.printStackTrace();
      }

      if (response.status() >= 400 && response.status() <= 499) {
        return new ClientException(response.reason(), response.status(), result);
      }
      if (response.status() >= 500 && response.status() <= 599) {
        return new RuntimeException(response.reason());
      }
      return errorStatus(methodKey, response);
    };
  }

  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${clientes.username:admin}") String username,
                                                                 @Value("${clientes.password:secreto}") String password) {
    return new BasicAuthRequestInterceptor(username, password);
  }
}
