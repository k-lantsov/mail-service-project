package com.example.coreservice.processor.impl;

import com.example.coreservice.model.NewMessageModel;
import com.example.coreservice.processor.NewMessageEventProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitNewMessageEventProcessor implements NewMessageEventProcessor {
    @Override
    public void processEvent(NewMessageModel newMessageModel) {
        log.info("Receive new message /// unique_message={}", newMessageModel.getUniqueMessage());

    }
}
