package com.example.coreservice.rabbitmq;

import com.example.coreservice.service.processor.NewMessageEventProcessor;
import com.example.coreservice.service.processor.StatusEventProcessor;
import com.example.shared.model.NewMessageEvent;
import com.example.shared.model.StatusEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMQConsumer {

    private final NewMessageEventProcessor newMessageEventProcessor;
    private final StatusEventProcessor statusEventProcessor;

    @RabbitListener(queues = "new-message-queue")
    public void receiveNewMessageEvent(NewMessageEvent newMessageEvent) {
        newMessageEventProcessor.processEvent(newMessageEvent);
    }

    @RabbitListener(queues = "status-queue")
    public void receiveStatusEvent(StatusEvent statusEvent) {
        statusEventProcessor.processEvent(statusEvent);
    }
}
