package com.circulosiete.curso.microservices.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import static java.lang.String.format;

public class MyHytrixCommand extends HystrixCommand<String> {
  private final String name;

  //Es importante que yo defina el constructor, para inicializar valores
  // de configuracion de Hystrix.
  public MyHytrixCommand(String name) {
    //GroupKey me sirve principalmente para definir el ThreadPool
    super(HystrixCommandGroupKey.Factory.asKey("GroupComando"));
    this.name = name;
  }

  @Override
  protected String run() throws Exception {
    //Aqui va la implementación de las llamadas a servicios remotos
    //Importante: NUNCA manejar excepciones.
    return format("Hola %s", name);
  }

  //Implemantar aqui que hacer si el comando falla
  @Override
  protected String getFallback() {
    return format("No se pudo ejecutar el saludo para %s", name);
  }
}
