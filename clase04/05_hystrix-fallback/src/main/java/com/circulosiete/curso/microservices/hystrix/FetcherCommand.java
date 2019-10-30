package com.circulosiete.curso.microservices.hystrix;

import com.circulosiete.curso.microservices.service.FetcherService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Objects;

public class FetcherCommand extends HystrixCommand<String> {
  private final FetcherService fetcherService;
  private final String url;

  public FetcherCommand(FetcherService fetcherService, String url) {
    //GroupKey indica el ThreadPool que usará
    super(HystrixCommandGroupKey.Factory.asKey("Fetcher"));
    this.fetcherService = fetcherService;
    this.url = url;
  }

  @Override
  protected String run() throws Exception {
    //Hay que evitar escribir la lógica de negocio,
    //Delegar a otro componente
    return fetcherService.fetch(url).getBody();
  }

  @Override
  protected String getFallback() {
    //Puedo obtener la excepcion que se lanzo en el metodo run
    //La excepcion obtenido en el run, puede ser null.
    Throwable failedExecutionException = getFailedExecutionException();

    //En caso de un timeout, puede ser un excepcion de infraestructura.
    Throwable executionException = this.getExecutionException();

    String mensaje = "No se que pàsa....";

    if(Objects.isNull(failedExecutionException)) {
      if(!Objects.isNull(executionException)) {
        mensaje = executionException.getMessage();
      }
    } else {
      mensaje = failedExecutionException.getMessage();
    }

    return "la url: " + url + ", no se pudo obtener, debido a: " + mensaje;
  }
}
