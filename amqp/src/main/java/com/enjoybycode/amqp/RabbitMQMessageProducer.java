package com.enjoybycode.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to exchange to {} using routingKey {}. Payload: {}", exchange, routingKey, routingKey);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to exchange to {} using routingKey {}. Payload: {}", exchange, routingKey, routingKey);
    }
}
