package com.circulosiete.curso.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//Habilita el soporte de Spring Cloud en SpringBoot (No es necesario en este punto)
@org.springframework.cloud.netflix.hystrix.EnableHystrix
//Habilita una UI con el dashboard para analizar las metricas generadas de Hystrix
@org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
public class IntroHystrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(IntroHystrixApplication.class, args);
  }
}
