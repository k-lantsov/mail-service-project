package com.example.coreservice.rabbitmq;

import com.example.coreservice.service.processor.NewMessageEventProcessor;
import com.example.coreservice.service.processor.StatusEventProcessor;
import com.example.coreservice.util.JsonConverter;
import com.example.shared.model.NewMessageEvent;
import com.example.shared.model.StatusEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMQConsumer {

    private final JsonConverter jsonConverter;
    private final NewMessageEventProcessor newMessageEventProcessor;
    private final StatusEventProcessor statusEventProcessor;

    @RabbitListener(queues = "new-message-queue")
    public void receiveNewMessageEvent(String message) {
        NewMessageEvent newMessageEvent = jsonConverter.deserializeFromJson(message, NewMessageEvent.class);
        newMessageEventProcessor.processEvent(newMessageEvent);
    }

    @RabbitListener(queues = "status-queue")
    public void receiveStatusEvent(String message) {
        StatusEvent statusEvent = jsonConverter.deserializeFromJson(message, StatusEvent.class);
        statusEventProcessor.processEvent(statusEvent);
    }
}
