package com.cacti.workshop.microservices;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.cacti.workshop.microservices.config.RabbitConfig.queueName;

@Component
public class Listener {

  @RabbitListener(queues = queueName)
  public void listen(Message message) {
    //message.getMessageProperties()
    String body = new String(message.getBody()) + " " + new Date();

    System.out.println(body);
  }
}
