package com.example.coreservice.rabbitmq;

import com.example.coreservice.model.NewMessageModel;
import com.example.coreservice.processor.NewMessageEventProcessor;
import com.example.coreservice.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMQConsumer {

    private final JsonConverter jsonConverter;
    private final NewMessageEventProcessor newMessageEventProcessor;

    @RabbitListener(queues = "newMessagesQueue")
    public void receiveNewMessageEvent(String message) {
        NewMessageModel newMessageModel = jsonConverter.deserializeFromJson(message, NewMessageModel.class);
        newMessageEventProcessor.processEvent(newMessageModel);
    }
}
