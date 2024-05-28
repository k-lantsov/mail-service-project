package com.example.coreservice.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public <T> void sendMessage(String routingKey, T t) {
        rabbitTemplate.convertAndSend(routingKey, t);
    }
}
