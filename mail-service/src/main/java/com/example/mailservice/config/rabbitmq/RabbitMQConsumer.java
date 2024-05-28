package com.example.mailservice.config.rabbitmq;

import com.example.mailservice.model.broker.MailEvent;
import com.example.mailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMQConsumer {

    private final MailService mailService;

    @RabbitListener(queues = "mail-queue")
    public void receiveMailEvent(MailEvent mailEvent) {
        if (mailEvent.file() == null) {
            mailService.sendSimpleMail(mailEvent);
        } else {
            mailService.sendMailWithAttachment(mailEvent);
        }
    }
}
