package com.example.coreservice.service.processor.impl;

import com.example.coreservice.service.db.MessageService;
import com.example.coreservice.service.processor.StatusEventProcessor;
import com.example.shared.model.StatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitStatusEventProcessor implements StatusEventProcessor {

    private final MessageService messageService;

    @Override
    public void processEvent(StatusEvent statusEvent) {
        log.info("Status event was received /// message_id={}", statusEvent.messageId());
        messageService.updateMessageStatus(statusEvent);
        log.info("Message status was updated /// message_id={}", statusEvent.messageId());
    }
}
