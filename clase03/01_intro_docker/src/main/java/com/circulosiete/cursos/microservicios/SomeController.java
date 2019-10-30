package com.circulosiete.cursos.microservicios;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SomeController {

  @RequestMapping(value = "/sse", produces = "text/event-stream")
  @ResponseStatus(HttpStatus.OK)
  public SseEmitter sse() {
    return new SseEmitter();
  }

  @RequestMapping(value = "/sse-send", produces = "text/event-stream")
  @ResponseStatus(HttpStatus.OK)
  public SseEmitter sseSend() throws Exception {
    SseEmitter sseEmitter = new SseEmitter();
    sseEmitter.send(SseEmitter.event().data("test data"));
    return sseEmitter;
  }

}
