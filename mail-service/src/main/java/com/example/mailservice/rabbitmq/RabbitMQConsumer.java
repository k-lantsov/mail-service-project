package com.example.mailservice.rabbitmq;

import com.example.mailservice.service.MailService;
import com.example.shared.model.MailEvent;
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
