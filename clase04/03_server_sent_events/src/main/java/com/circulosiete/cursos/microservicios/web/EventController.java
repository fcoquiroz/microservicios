package com.circulosiete.cursos.microservicios.web;

import com.circulosiete.cursos.microservicios.service.SseService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class EventController {

  private final SseService sseService;

  public EventController(SseService sseService) {
    this.sseService = sseService;
  }

  @GetMapping("/stream/{counter}")
  public SseEmitter stream(@PathVariable("counter") Integer counter) throws Exception {
    StopWatch stopWatch = new StopWatch("stream controller");
    stopWatch.start();
    //Cree una instancia por petición
    SseEmitter emitter = new SseEmitter();

    sseService.queueWork(emitter, counter);

    stopWatch.stop();
    emitter.send(stopWatch.shortSummary());

    return emitter;
  }
}
