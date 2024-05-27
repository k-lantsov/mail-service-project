package com.example.coreservice.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue newMessagesQueue() {
        return new Queue("new-messages-queue");
    }

    @Bean
    public Queue mailsQueue() {
        return new Queue("mails-queue");
    }

    @Bean
    public Queue statusQueue() {
        return new Queue("status-queue");
    }
}
