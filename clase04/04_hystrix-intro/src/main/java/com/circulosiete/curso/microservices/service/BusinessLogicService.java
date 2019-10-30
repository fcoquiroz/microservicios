package com.circulosiete.curso.microservices.service;

import com.circulosiete.curso.microservices.hystrix.LatencyCommand;
import com.circulosiete.curso.microservices.hystrix.MyHytrixCommand;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Service
public class BusinessLogicService {

  public String saludo(String name, String modo) throws Exception {

    // Se debe instanciar un comando de Hystrix en cada ejecución
    MyHytrixCommand command = new MyHytrixCommand(name);
    String result;

    switch (modo) {
      case "futuro":
        //Resultado obtenido del comando de forma NO-bloqueante
        java.util.concurrent.Future<String> future = command.queue();

        //Aqui podria poner codigo que hiciera otras cosas, en lo que el Futuro se "resuelve"
        //......
        //.....

        //En algun momento obtengo el valor del futuro (operacion bloqueante)
        result = future.get();

        break;
      default:
        //Resultado obtenido del comando de forma bloqueante
        result = command.execute();
        break;
    }

    result = String.format("%s, Ejecutado en modo %s", result, modo);

    return result;
  }


  public String saludoObservable(String name) throws Exception {

    List<String> buffer = new ArrayList<>();


    LatencyCommand latencyCommand = new LatencyCommand(name, 200l);
    Observable<String> observe = latencyCommand.observe();

    observe.subscribe(buffer::add);


    LatencyCommand latencyCommand2 = new LatencyCommand(name, 250l);
    Observable<String> observe2 = latencyCommand2.observe();

    observe2.subscribe(buffer::add);


    LatencyCommand latencyCommand3 = new LatencyCommand(name, 300l);

    String resultWithMoreLatency = latencyCommand3.toObservable().toBlocking().first();
    buffer.add(resultWithMoreLatency);


    return buffer.stream().collect(joining(", "));
  }

}
