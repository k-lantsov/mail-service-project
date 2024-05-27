package com.example.coreservice.service.processor;

import com.example.shared.model.NewMessageEvent;

public interface NewMessageEventProcessor {

    void processEvent(NewMessageEvent newMessageEvent);
}
