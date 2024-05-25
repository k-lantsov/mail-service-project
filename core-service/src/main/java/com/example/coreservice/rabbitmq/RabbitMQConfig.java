package com.example.coreservice.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue newMessagesQueue() {
        return new Queue("newMessagesQueue");
    }

    @Bean
    public Queue processedMessagesQueue() {
        return new Queue("processedMessagesQueue");
    }
}
