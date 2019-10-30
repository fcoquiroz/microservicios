package com.cacti.workshop.microservices;

import com.cacti.workshop.microservices.config.RabbitConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
  private final RabbitTemplate rabbitTemplate;

  public ProducerController(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @PostMapping("/queue")
  public HttpEntity queue() {
    rabbitTemplate.convertAndSend(RabbitConfig.queueName, "Hola desde RabbitMQ!");
    /*rabbitTemplate.convertAndSend((Object)"s", new MessagePostProcessor() {
      @Override
      public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setHeader("lla", "ddd");
        return message;
      }
    });*/

    return ResponseEntity.ok(true);
  }
}
