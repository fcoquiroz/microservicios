package com.circulosiete.curso.microservices.web;

import com.circulosiete.curso.microservices.service.BusinessLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
  @Autowired
  BusinessLogicService businessLogicService;

  @RequestMapping("/sinrx/{name}")
  public String foo(@PathVariable("name") String name, @RequestParam(name = "modo", defaultValue = "bloqueante") String modo) throws Exception {
    return businessLogicService.saludo(name, modo);
  }

  @RequestMapping("/rx/{name}")
  public String bar(@PathVariable("name") String name) throws Exception {
    StopWatch stopWatch = new StopWatch("conObservable");
    stopWatch.start();
    String result = businessLogicService.saludoObservable(name);
    stopWatch.stop();

    System.out.println(stopWatch.shortSummary());
    return result;
  }
}
